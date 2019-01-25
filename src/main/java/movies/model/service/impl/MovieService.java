package movies.model.service.impl;

import lombok.AllArgsConstructor;
import movies.infra.repository.CreateRepository;
import movies.infra.repository.ListAllRepository;
import movies.infra.repository.projection.MovieProjection;
import movies.model.Movie;
import movies.model.service.CreateService;
import movies.model.service.ListAllService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class MovieService implements
        CreateService<Movie>,
        ListAllService<MovieProjection> {

    private CreateRepository<Movie> createRepository;
    private ListAllRepository<MovieProjection> listAllRepository;

    @Override
    @Transactional
    public Movie create(Movie object) {
        return createRepository.create(object);
    }

    @Override
    public List<MovieProjection> all() {
        return listAllRepository.all();
    }
}
