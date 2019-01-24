package movies.model.service;

public interface FindOneService<T> {

    T findOne(Long id);

}
