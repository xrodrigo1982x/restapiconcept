package movies.view.dto;

import java.util.function.Function;

public interface DTOMapper<T, D> extends Function<T, D> {

    D map(T source);

    @Override
    default D apply(T t){
        return this.map(t);
    }
}
