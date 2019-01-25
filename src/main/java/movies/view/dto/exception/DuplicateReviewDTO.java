package movies.view.dto.exception;

public class DuplicateReviewDTO {
    public Long userId;
    public String userName;
    public Long movieId;
    public String movieName;
    public String getMessage(){
        return "review already exists for " + movieName;
    }
}
