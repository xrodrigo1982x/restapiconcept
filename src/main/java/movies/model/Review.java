package movies.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import movies.infra.repository.pk.ReviewPK;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "unique_user_movie", columnNames = {"movie_id", "user_id"})})
public class Review {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "generic_seq")
    @SequenceGenerator(name = "generic_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "review_user"))
    private User user;
    private LocalDateTime date;
    private Integer rating;
    private String comment;

}
