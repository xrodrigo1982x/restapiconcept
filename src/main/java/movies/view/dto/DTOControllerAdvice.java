package movies.view.dto;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import java.util.function.Function;

import static java.util.stream.StreamSupport.stream;

@ControllerAdvice
@AllArgsConstructor
public class DTOControllerAdvice extends AbstractMappingJacksonResponseBodyAdvice {

    private ApplicationContext context;
    private GenericMapper genericMapper;

    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue value, MediaType mediaType,
                                           MethodParameter methodParameter, ServerHttpRequest serverHttpRequest,
                                           ServerHttpResponse serverHttpResponse) {
        Function mapper = null;

        UsingDTOMapper mapperClass = methodParameter.getMethodAnnotation(UsingDTOMapper.class);
        if (mapperClass != null) {
            mapper = context.getBean(mapperClass.value());
        } else {
            MapToDTO mapToDTOClass = methodParameter.getMethodAnnotation(MapToDTO.class);
            Assert.notNull(mapToDTOClass, "Method has no mapper nor dto class declared");
            mapper = o -> genericMapper.map(o, mapToDTOClass.value());
        }

        Object returnValue = handleByType(value.getValue(), mapper);
    }


    private Object handleByType(Object value, Function mapper) {
        Object returnValue;
        if (value instanceof Page)
            returnValue = ((Page) value).map(mapper);
        else if (value instanceof Iterable)
            returnValue = stream(((Iterable) value).spliterator(), false).map(mapper);
        else
            returnValue = mapper.apply(value);

        return returnValue;
    }

}
