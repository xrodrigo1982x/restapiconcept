package movies.infra.extra;

import org.apache.catalina.connector.RequestFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.MDC;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class RequestIdFilterTest {

    private RequestIdFilter idFilter = new RequestIdFilter();
    @Mock
    private RequestFacade request;
    @Mock
    private ServletResponse response;
    @Mock
    private FilterChain chain;

    @Test
    public void txIdIsCreated() throws IOException, ServletException {
        doReturn(null).when(request).getHeader(anyString());
        doAnswer(invocationOnMock -> {
            assertThat(MDC.getCopyOfContextMap()).isNotEmpty();
            return null;
        }).when(chain).doFilter(request, response);
        idFilter.doFilter(request, response, chain);
    }

    @Test
    public void txIdIsProvided() throws IOException, ServletException {
        doReturn("foo").when(request).getHeader(anyString());
        doAnswer(invocationOnMock -> {
            assertThat(MDC.getCopyOfContextMap()).containsValues("foo");
            return null;
        }).when(chain).doFilter(request, response);
        idFilter.doFilter(request, response, chain);
    }

    @Test
    public void mdcIsCleanAfterExecution() throws IOException, ServletException {
        doReturn(null).when(request).getHeader(anyString());
        idFilter.doFilter(request, response, chain);
        assertThat(MDC.getCopyOfContextMap()).isNullOrEmpty();
    }
}