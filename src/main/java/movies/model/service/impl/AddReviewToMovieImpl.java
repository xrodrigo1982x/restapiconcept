package movies.model.service.impl;

import lombok.AllArgsConstructor;
import movies.model.AddReviewToMovie;
import movies.model.Movie;
import movies.model.ReviewInput;
import movies.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AddReviewToMovieImpl implements AddReviewToMovie {

    @Override
    @Transactional
    public void apply(Movie movie, ReviewInput reviewInput) {
        movie.addReview(reviewInput);
    }
}
