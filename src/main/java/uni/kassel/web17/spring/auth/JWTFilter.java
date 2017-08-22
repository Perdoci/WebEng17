package uni.kassel.web17.spring.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends GenericFilterBean {
    private static final Logger LOG = LoggerFactory.getLogger(JWTFilter.class);

    private AuthentificationService authService;

    public JWTFilter(AuthentificationService authService) {
        this.authService = authService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        LOG.info("Request: " + httpServletRequest.getRequestURI());
        // TODO ML Add validation logic for JWT token.

        filterChain.doFilter(request, response);
    }
}
