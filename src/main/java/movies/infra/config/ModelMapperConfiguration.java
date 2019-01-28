package movies.infra.config;

import movies.model.Director;
import movies.model.Genre;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

import java.util.List;

import static java.util.Arrays.asList;
import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
public class ModelMapperConfiguration {

    private static final Class DEFAULT_ID_CLAZZ = Long.class;
    private static final List<Class> ENTITIES = asList(Genre.class, Director.class);

    @Bean
    public ModelMapper modelMapper(ConversionService service) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setMethodAccessLevel(PRIVATE);

        ENTITIES.forEach(aClass -> {
            if (service.canConvert(aClass, DEFAULT_ID_CLAZZ) && service.canConvert(aClass, DEFAULT_ID_CLAZZ))
                modelMapper.addConverter(context -> service.convert(context.getSource(), DEFAULT_ID_CLAZZ), aClass, DEFAULT_ID_CLAZZ);

            if (service.canConvert(DEFAULT_ID_CLAZZ, aClass) && service.canConvert(DEFAULT_ID_CLAZZ, aClass))
                modelMapper.addConverter(context -> service.convert(context.getSource(), aClass), DEFAULT_ID_CLAZZ, aClass);
        });


        return modelMapper;
    }

}
