package movies.infra.repository.jpa;

import movies.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewJpaRepository extends CrudRepository<Review, Long> {
}
