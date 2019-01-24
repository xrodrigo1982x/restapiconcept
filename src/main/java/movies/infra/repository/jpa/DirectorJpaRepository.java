package movies.infra.repository.jpa;

import movies.model.Director;
import org.springframework.data.repository.CrudRepository;

public interface DirectorJpaRepository extends CrudRepository<Director, Long> {
}
