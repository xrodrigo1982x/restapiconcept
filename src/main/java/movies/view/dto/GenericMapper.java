package movies.view.dto;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GenericMapper {

    private ModelMapper mapper;

    public <T, D> D map(T source, Class<D> clazz) {
        return mapper.map(source, clazz);
    }

}
