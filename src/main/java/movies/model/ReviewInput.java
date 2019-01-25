package movies.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public class ReviewInput {
    public String comment;
    public User user;
    public Integer rating;
}
