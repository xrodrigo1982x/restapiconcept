package movies.view.dto;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@AllArgsConstructor
public class GenericEntityMapper {

    private ModelMapper mapper;

    public <T, D> Supplier<T> getMapper(T current, D dto) {
        return () -> {
            mapper.map(dto, current);
            return current;
        };
    }

}
