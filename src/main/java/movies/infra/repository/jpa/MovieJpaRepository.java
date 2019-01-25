package movies.infra.repository.jpa;

import movies.infra.repository.CreateRepository;
import movies.infra.repository.ListAllRepository;
import movies.infra.repository.projection.MovieProjection;
import movies.model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieJpaRepository extends CrudRepository<Movie, Long>,
        CreateRepository<Movie>,
        ListAllRepository<MovieProjection> {

    @Override
    default Movie create(Movie object){
        return this.save(object);
    }

    @Override
    default List<MovieProjection> all(){
        return this.findAllProjectedByOrderByNameAsc();
    }

    List<MovieProjection> findAllProjectedByOrderByNameAsc();

}
