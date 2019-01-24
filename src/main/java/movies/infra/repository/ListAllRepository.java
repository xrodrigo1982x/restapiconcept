package movies.infra.repository;

import java.util.List;

public interface ListAllRepository<T, D> {

    List<D> all();

}
