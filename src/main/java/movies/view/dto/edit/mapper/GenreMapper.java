package movies.view.dto.edit.mapper;

import movies.model.Genre;
import movies.view.dto.EntityMapper;
import movies.view.dto.edit.GenreEditDTO;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper implements EntityMapper<Genre, GenreEditDTO> {

    @Override
    public Genre map(Genre current, GenreEditDTO dto) {
        current.setName(dto.name);
        return current;
    }

}
