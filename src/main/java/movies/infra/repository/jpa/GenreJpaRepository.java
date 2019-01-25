package movies.infra.repository.jpa;

import movies.infra.repository.CreateRepository;
import movies.infra.repository.FindOneRepository;
import movies.infra.repository.ListAllRepository;
import movies.infra.repository.projection.GenreProjection;
import movies.model.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreJpaRepository extends CrudRepository<Genre, Long>,
        FindOneRepository<Genre>,
        ListAllRepository<GenreProjection>,
        CreateRepository<Genre> {

    List<GenreProjection> findAllProjectedByOrderByNameAsc();

    @Override
    default Genre findOne(Long id) {
        return this.findById(id).orElse(null);
    }

    @Override
    default List<GenreProjection> all() {
        return this.findAllProjectedByOrderByNameAsc();
    }

    @Override
    default Genre create(Genre object){
        return this.save(object);
    }
}
