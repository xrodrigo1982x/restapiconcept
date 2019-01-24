package movies.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import movies.infra.repository.pk.ReviewPK;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@IdClass(ReviewPK.class)
public class Review {

    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "review_movie"))
    private Movie movie;
    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "review_user"))
    private User user;
    private LocalDateTime date;
    private Integer rating;
    private String comment;

}
