package movies.model.service.impl;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.Supplier;

@Service
public class UpdateService {

    @Transactional
    public <T> T update(Supplier<T> updateSupplier) {
        return updateSupplier.get();
    }

}
