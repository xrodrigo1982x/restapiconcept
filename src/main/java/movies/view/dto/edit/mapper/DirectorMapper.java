package movies.view.dto.edit.mapper;

import movies.model.Director;
import movies.view.dto.EntityMapper;
import movies.view.dto.edit.DirectorEditDTO;
import org.springframework.stereotype.Component;

@Component
public class DirectorMapper implements EntityMapper<Director, DirectorEditDTO> {
    @Override
    public Director map(Director current, DirectorEditDTO dto) {
        current.setName(dto.name);
        return current;
    }
}
