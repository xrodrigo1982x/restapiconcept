package movies.model.service;

import java.util.List;

public interface ListAllService<T, D> {
    List<D> all();
}
