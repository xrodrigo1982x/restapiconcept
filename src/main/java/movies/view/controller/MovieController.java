package movies.view.controller;

import movies.model.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class MovieController {

    @PostMapping("{movie}")
    public void addReview(@PathVariable Movie movie, ReviewDTO)

}
