package movies.model.service.impl;

import lombok.AllArgsConstructor;
import movies.infra.repository.CreateRepository;
import movies.infra.repository.FindOneRepository;
import movies.infra.repository.ListAllRepository;
import movies.infra.repository.projection.GenreProjection;
import movies.model.Genre;
import movies.model.service.CreateService;
import movies.model.service.FindOneService;
import movies.model.service.ListAllService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@AllArgsConstructor
public class GenreService implements
        FindOneService<Genre>,
        ListAllService<GenreProjection>,
        CreateService<Genre> {

    private FindOneRepository<Genre> findOneRepository;
    private ListAllRepository<GenreProjection> listAllRepository;
    private CreateRepository<Genre> createRepository;

    @Override
    public Genre findOne(Long id) {
        return findOneRepository.findOne(id);
    }

    @Override
    public List<GenreProjection> all() {
        return listAllRepository.all();
    }

    @Override
    @Transactional
    public Genre create(Genre object) {
        return createRepository.create(object);
    }
}
