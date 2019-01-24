package movies.view.dto;

public interface EntityMapper<T, D>  {

    T map(T current, D dto);

}
