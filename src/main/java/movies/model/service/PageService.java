package movies.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PageService<T, P> {
    Page<P> list(Pageable pageable);
}
