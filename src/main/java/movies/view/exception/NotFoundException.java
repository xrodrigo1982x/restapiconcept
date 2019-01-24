package movies.view.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(code = NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public static <T> void verifyFound(T o) {
        if (o == null)
            throw new NotFoundException();
    }

    public static <T> void verifyFound(Optional<T> o) {
        if (!o.isPresent())
            throw new NotFoundException();
    }

}
