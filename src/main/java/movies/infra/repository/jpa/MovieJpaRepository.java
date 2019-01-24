package movies.infra.repository.jpa;

import movies.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieJpaRepository extends CrudRepository<Movie, Long> {
}
