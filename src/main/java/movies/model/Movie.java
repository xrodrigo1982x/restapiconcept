package movies.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "generic_seq")
    @SequenceGenerator(name = "generic_seq", allocationSize = 1)
    private Long id;
    private String name;
    private LocalDate releaseDate;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "movie_director"))
    private Director director;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "movie_genre"))
    private Genre genre;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "movie_id")
    private List<Review> reviews = new ArrayList<>();

    public void addReview(ReviewInput input){
        Review review = Review.builder()
                .date(LocalDateTime.now())
                .rating(input.rating)
                .comment(input.comment)
                .user(input.user)
                .build();
        reviews.add(review);
    }

}
