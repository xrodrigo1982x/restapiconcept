package movies.infra.repository.jpa;

import movies.infra.repository.ListAllRepository;
import movies.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserJpaRepository extends CrudRepository<User, Long>,
        ListAllRepository<User> {
    @Override
    default List<User> all(){
        return (List<User>) this.findAll();
    }
}
