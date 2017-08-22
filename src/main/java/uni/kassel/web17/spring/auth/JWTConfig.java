package uni.kassel.web17.spring.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTConfig {
    @Autowired
    private AuthentificationService authService;

    @Bean
    public FilterRegistrationBean jwtFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new JWTFilter(authService));
        bean.addUrlPatterns("/api/*");
        return bean;
    }
}
