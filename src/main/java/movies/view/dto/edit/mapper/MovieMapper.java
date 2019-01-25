package movies.view.dto.edit.mapper;

import lombok.AllArgsConstructor;
import movies.model.Director;
import movies.model.Genre;
import movies.model.Movie;
import movies.model.service.FindOneService;
import movies.view.dto.EntityMapper;
import movies.view.dto.edit.MovieEditDTO;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MovieMapper implements EntityMapper<Movie, MovieEditDTO> {

    private FindOneService<Genre> genreFindOneService;
    private FindOneService<Director> directorFindOneService;

    @Override
    public Movie map(Movie current, MovieEditDTO dto) {
        current.setName(dto.name);
        current.setReleaseDate(dto.releaseDate);
        current.setDirector(dto.directorId != null ? directorFindOneService.findOne(dto.directorId) : null);
        current.setGenre(dto.genreId != null ? genreFindOneService.findOne(dto.genreId) : null);
        return current;
    }
}
