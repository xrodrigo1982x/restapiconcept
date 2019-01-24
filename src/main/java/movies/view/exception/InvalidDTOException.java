package movies.view.exception;

import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@AllArgsConstructor
public class InvalidDTOException extends RuntimeException {

    private BindingResult result;

    public Map<String, List<String>> getValidationErrors() {
        return result.getFieldErrors().stream()
                .collect(groupingBy(FieldError::getField, mapping(DefaultMessageSourceResolvable::getDefaultMessage, toList())));
    }

    public static void verifyValidationErrors(BindingResult result) {
        if (result.hasErrors())
            throw new InvalidDTOException(result);
    }

}
