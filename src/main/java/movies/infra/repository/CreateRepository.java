package movies.infra.repository;

public interface CreateRepository<T> {

    T create(T object);

}
