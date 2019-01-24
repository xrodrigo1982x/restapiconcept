package movies.view.dto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Configuration
@Slf4j
public class DTOMappingControllerValidator {

    @Autowired
    public void validateMethods(ListableBeanFactory listableBean, ConfigurableApplicationContext ctx) {
        Map<String, Object> controllers = listableBean.getBeansWithAnnotation(RestController.class);
        controllers.forEach((s, o) -> {
            List invalidMethods = validateDTOMapping(o);
            if (!invalidMethods.isEmpty()) {
                log.error(s + " returns type not mapped to DTO");
                ctx.close();
            }
        });
    }

    private List<Method> validateDTOMapping(Object o) {
        return Stream.of(o.getClass().getDeclaredMethods())
                .filter(this::methodIsNotValid)
                .collect(toList());
    }

    private boolean methodIsNotValid(Method method){
        return !(!"void".equals(method.getReturnType().getName())
                && (method.isAnnotationPresent(UsingDTOMapper.class) || method.isAnnotationPresent(MapToDTO.class)));
    }

}
