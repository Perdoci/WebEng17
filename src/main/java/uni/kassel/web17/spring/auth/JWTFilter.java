package uni.kassel.web17.spring.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;
import uni.kassel.web17.spring.user.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends GenericFilterBean {
    private static final Logger LOG = LoggerFactory.getLogger(JWTFilter.class);

    private AuthentificationService authService;
    private UserService userService;

    public JWTFilter(AuthentificationService authService, UserService userService) {

        this.authService = authService;
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String auth = httpServletRequest.getHeader("Authorization");

        if (!StringUtils.startsWith(auth, "Bearer ")) {
            // Allow requests without a token.

            userService.setAnonymous();
            filterChain.doFilter(request, response);
            return;
        }

        // Extract token contents.
        String token = auth.substring(7);
        try {
            Claims body = (Claims) authService.parseToken(token);
            String email = body.getSubject();
            String id = body.getId();
            //Set user globally for following operations.
            userService.setCurrentUser(Integer.parseInt(body.getId()), body.getSubject());
            filterChain.doFilter(request, response);
        } catch (SignatureException e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
