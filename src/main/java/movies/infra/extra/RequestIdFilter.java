package movies.infra.extra;

import org.apache.catalina.connector.RequestFacade;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.defaultIfEmpty;


@Component
public class RequestIdFilter implements Filter {

    public static final String TX_ID = "txId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String txId = getOrCreateTxId(((RequestFacade) request).getHeader(TX_ID));
            MDC.put(TX_ID, txId);
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

    private String getOrCreateTxId(String givenTxId) {
        return defaultIfEmpty(givenTxId, UUID.randomUUID().toString());
    }
}
