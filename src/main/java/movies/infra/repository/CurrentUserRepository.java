package movies.infra.repository;

import movies.model.User;

import java.util.function.Supplier;

public interface CurrentUserRepository extends Supplier<User> {
}
