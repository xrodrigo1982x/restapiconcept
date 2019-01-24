package movies.model.service;

import movies.infra.repository.SearchCriteria;
import org.springframework.data.domain.Page;

public interface SearchService<T, P> {
    Page<P> search(SearchCriteria<T> criteria);
}
