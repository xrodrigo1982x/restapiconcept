package movies.infra.repository;

import org.springframework.data.domain.Page;

public interface SearchRepository<T, P> {

    Page<P> search(SearchCriteria<T> criteria);

}
