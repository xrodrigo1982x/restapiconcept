package movies.infra.config;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movies.infra.repository.jpa.*;
import movies.model.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

@Component
@AllArgsConstructor
@Slf4j
public class MockDataConfig {

    private static final Faker FAKER = new Faker();

    private static final Integer MOVIE_COUNT = 10;
    private static final Integer GENRE_COUNT = 3;
    private static final Integer DIRECTOR_COUNT = 10;
    private static final Integer USER_COUNT = 10;
    private static final Integer REVIEW_COUNT = 1000;

    private DirectorJpaRepository directorJpaRepository;
    private GenreJpaRepository genreJpaRepository;
    private MovieJpaRepository movieJpaRepository;
    private ReviewJpaRepository reviewJpaRepository;
    private UserJpaRepository userJpaRepository;

    @PostConstruct
    public void init() {
        List<Genre> genres = (List<Genre>) genreJpaRepository.saveAll(getGenres());
        log.info("genres created");
        List<Director> directors = (List<Director>) directorJpaRepository.saveAll(getDirectors());
        log.info("directors created");
        List<User> users = (List<User>) userJpaRepository.saveAll(getUsers());
        List<Movie> movies = (List<Movie>) movieJpaRepository.saveAll(getMovies(directors, genres));
    }

    private LocalDateTime getDate(Movie movie) {
        Date releaseAsDate = Date.from(movie.getReleaseDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date date = FAKER.date().between(releaseAsDate, new Date());
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private List<Movie> getMovies(List<Director> directors, List<Genre> genres) {
        return range(0, MOVIE_COUNT).mapToObj(i -> Movie.builder()
                .name(FAKER.lorem().word())
                .director(FAKER.options().nextElement(directors))
                .genre(FAKER.options().nextElement(genres))
                .name(FAKER.book().title())
                .releaseDate(LocalDate.now().minus(FAKER.number().numberBetween(0, 40 * 365), ChronoUnit.DAYS))
                .build()).collect(toList());
    }

    private List<User> getUsers() {
        return range(0, USER_COUNT).mapToObj(i -> User.builder()
                .name(FAKER.name().fullName())
                .username(FAKER.name().username())
                .build()).collect(toList());
    }

    private List<Director> getDirectors() {
        return range(0, DIRECTOR_COUNT).mapToObj(i -> Director.builder().name(FAKER.name().fullName()).build()).collect(toList());
    }

    private List<Genre> getGenres() {
        return range(0, GENRE_COUNT).mapToObj(i -> Genre.builder().name(FAKER.lorem().word()).build()).collect(toList());
    }

}
