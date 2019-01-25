package movies.infra.repository.jpa;

import movies.infra.repository.FindOneRepository;
import movies.model.Director;
import org.springframework.data.repository.CrudRepository;

public interface DirectorJpaRepository extends CrudRepository<Director, Long>,
        FindOneRepository<Director> {

    @Override
    default Director findOne(Long id) {
        return this.findById(id).orElse(null);
    }
}
