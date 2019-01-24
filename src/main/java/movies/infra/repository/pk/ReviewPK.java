package movies.infra.repository.pk;

import com.fasterxml.jackson.databind.SerializationConfig;
import lombok.Data;
import movies.model.Movie;
import movies.model.User;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ReviewPK implements Serializable {

    private Movie movie;
    private User user;

}
