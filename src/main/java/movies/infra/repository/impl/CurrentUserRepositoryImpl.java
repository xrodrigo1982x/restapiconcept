package movies.infra.repository.impl;

import lombok.AllArgsConstructor;
import movies.infra.repository.CurrentUserRepository;
import movies.infra.repository.ListAllRepository;
import movies.model.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
public class CurrentUserRepositoryImpl implements CurrentUserRepository {

    private ListAllRepository<User> listAllRepository;

    @Override
    public User get() {
        List<User> users = listAllRepository.all();
        Collections.shuffle(users);
        return users.get(0);
    }
}
