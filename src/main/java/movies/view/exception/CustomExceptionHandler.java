package movies.view.exception;

import movies.view.dto.MapToDTO;
import movies.view.dto.exception.DuplicateReviewDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidDTOException.class)
    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    public Map handle(InvalidDTOException exception) {
        return exception.getValidationErrors();
    }

    @ExceptionHandler(DuplicateReviewException.class)
    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @MapToDTO(DuplicateReviewDTO.class)
    public DuplicateReviewException handle(DuplicateReviewException exception) {
        return exception;
    }

}
