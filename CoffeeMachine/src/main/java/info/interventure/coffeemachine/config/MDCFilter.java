package info.interventure.coffeemachine.config;

import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Configuration
public class MDCFilter extends OncePerRequestFilter {

    private static final String CORRELATION_ID_HEADER = "X-Correlation-ID";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String correlationId = request.getHeader(CORRELATION_ID_HEADER);
            if (!StringUtils.hasText(correlationId)) {
                correlationId = UUID.randomUUID().toString();
            }
            addToMDC(correlationId);
            filterChain.doFilter(request, response);
        } finally {
            removeFromMDC();
        }
    }

    private void addToMDC(String correlationId) {
        MDC.put(CORRELATION_ID_HEADER, correlationId);
    }

    private void removeFromMDC() {
        MDC.remove(CORRELATION_ID_HEADER);
    }
}
