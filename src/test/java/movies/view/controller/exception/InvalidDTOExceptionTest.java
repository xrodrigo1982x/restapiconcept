package movies.view.controller.exception;

import movies.view.exception.InvalidDTOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class InvalidDTOExceptionTest {

    @Mock
    private BindingResult result;
    private static final List<FieldError> ERROR_LIST = Collections.singletonList(new FieldError("", "foo", "error"));

    @Test(expected = InvalidDTOException.class)
    public void testExceptionIsThrownOnErrors(){
        doReturn(true).when(result).hasErrors();
        InvalidDTOException.verifyValidationErrors(result);
    }

    @Test
    public void testItReturnsErrorMap(){
        doReturn(true).when(result).hasErrors();
        doReturn(ERROR_LIST).when(result).getFieldErrors();

        try {
            InvalidDTOException.verifyValidationErrors(result);
        }catch (InvalidDTOException e){
            Map<String, List<String>> output = e.getValidationErrors();
            assertThat(output).containsKey("foo");
            assertThat(output.get("foo")).contains("error");
        }
    }

}