package movies.model;

public interface AddReviewToMovie {

    void addReviewToMovie(Movie movie, String comment, User user,  Integer rating);

}
