package movies.infra.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PageRepository<T, P> {

    Page<P> list(Pageable pageable);

}
