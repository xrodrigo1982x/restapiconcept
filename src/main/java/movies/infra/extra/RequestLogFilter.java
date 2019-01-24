package movies.infra.extra;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

import static net.logstash.logback.marker.Markers.append;

@Component
@Slf4j
public class RequestLogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info(
                append("parameters", servletRequest.getParameterMap())
                        .and(append("url", ((RequestFacade) servletRequest).getServletPath()))
                        .and(append("method", ((RequestFacade) servletRequest).getMethod())),
                "Request data");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
