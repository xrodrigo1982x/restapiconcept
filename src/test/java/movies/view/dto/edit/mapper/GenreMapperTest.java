package movies.view.dto.edit.mapper;

import movies.model.Genre;
import movies.view.dto.EntityMapper;
import movies.view.dto.edit.GenreEditDTO;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GenreMapperTest {

    private Genre genre = getEntity();

    private GenreEditDTO dto = getDTO();

    private EntityMapper<Genre, GenreEditDTO> mapper = new GenreMapper();

    @Test
    public void map() {
        genre = mapper.map(genre, dto);
        assertThat(genre.getName()).isEqualTo(dto.name);
    }

    private GenreEditDTO getDTO() {
        GenreEditDTO dto = new GenreEditDTO();
        dto.name = "b";
        return dto;
    }

    private Genre getEntity() {
        return Genre.builder()
                .name("a")
                .build();
    }
}