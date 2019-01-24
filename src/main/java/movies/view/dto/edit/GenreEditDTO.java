package movies.view.dto.edit;

import javax.validation.constraints.NotEmpty;

public class GenreEditDTO {
    public Long id;
    @NotEmpty
    public String name;
}
