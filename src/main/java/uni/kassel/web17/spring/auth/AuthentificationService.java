package uni.kassel.web17.spring.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uni.kassel.web17.spring.user.User;
import uni.kassel.web17.spring.user.UserService;

@Service
public class AuthentificationService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthentificationService.class);


    @Value("${authenticationService.salt}")
    private String salt;
    @Value("${authenticationService.jwt.secret}")
    private String SECRET;


    @Autowired
    private UserService userService;


    public Object parseToken(String jwtToken) {
        LOG.debug("Parsing JWT token. JWTtoken={}", jwtToken);
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parse(jwtToken)
                .getBody();
    }

    public void setUser(Integer id, String email) {
        LOG.debug("Setting user context. id={}, user={}", id, email);

        User user = new User();
        user.setId(id);
        user.setEmail(email);
        UsernamePasswordAuthenticationToken secAuth = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(secAuth);
    }
    public static class UserToken {
        public User user;
        public String token;
    }

    public UserToken login(String email, String password) {
        String hashedPassword = hashPassword(password);
        User user = userService.getUser(email, hashedPassword);
        if (user == null) {
            LOG.info("User unable to login. user={}", email);
            return null;
        }
        LOG.info("User successfully logged in. user={}", email);


        String token = Jwts.builder().setSubject(email).setId(user.getEmail().toString()).signWith(SignatureAlgorithm.HS512, SECRET).compact();

        UserToken userToken = new UserToken();
        userToken.user = user;
        userToken.token = token;
        return userToken;
    }

    private String hashPassword(String password) {
        return DigestUtils.sha512Hex(salt + password);

    }
}
