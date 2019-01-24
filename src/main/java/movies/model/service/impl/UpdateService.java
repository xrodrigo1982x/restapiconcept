package movies.model.service.impl;

import movies.view.dto.EntityMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UpdateService {

    @Transactional
    public <T, D> T update(T source, D with, EntityMapper<T, D> mapper) {
        return mapper.map(source, with);
    }

}
