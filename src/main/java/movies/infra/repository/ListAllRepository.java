package movies.infra.repository;

import java.util.List;

public interface ListAllRepository<D> {

    List<D> all();

}
