package movies.model.service.impl;

import lombok.AllArgsConstructor;
import movies.infra.repository.FindOneRepository;
import movies.model.Director;
import movies.model.service.FindOneService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DirectorService implements
        FindOneService<Director> {

    private FindOneRepository<Director> directorFindOneRepository;

    @Override
    public Director findOne(Long id) {
        return directorFindOneRepository.findOne(id);
    }
}
