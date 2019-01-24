package movies.model.service.impl;

import movies.model.AddReviewToMovie;
import movies.model.Movie;
import movies.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AddReviewToMovieImpl implements AddReviewToMovie {
    @Override
    @Transactional
    public void addReviewToMovie(Movie movie, String comment, User user, Integer rating) {
        movie.addReview(rating, comment, user);
    }
}
