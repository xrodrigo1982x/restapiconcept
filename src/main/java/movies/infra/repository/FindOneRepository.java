package movies.infra.repository;

public interface FindOneRepository<T> {

    T findOne(Long id);

}
