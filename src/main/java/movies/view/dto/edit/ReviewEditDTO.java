package movies.view.dto.edit;

import java.time.LocalDateTime;

public class ReviewEditDTO  {
    public Long movieId;
    public Long userId;
    public LocalDateTime date;
    public Integer rating;
    public String comment;
}
