package movies.view.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import movies.model.Movie;
import movies.model.User;

@AllArgsConstructor
@Getter
public class DuplicateReviewException extends RuntimeException {

    private User user;
    private Movie movie;

}
