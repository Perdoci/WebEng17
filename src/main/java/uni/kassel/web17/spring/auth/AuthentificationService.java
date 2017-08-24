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

    private String salt = "0a93a6adb449fc33511bf00ed9b9e94716bd8ff1eaf64634bdcc185fab4d79656f6f1087c0ea2525662859c52f048fbdbcfff49d7bbf913dbcf325a10dcfafd5";
    private String SECRET = "this is not a secret";


    @Autowired
    private UserService userService;


    public Object parseToken(String jwtToken) {
        LOG.debug("Parsing JWT token. JWTtoken={}", jwtToken);
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parse(jwtToken)
                .getBody();
    }

    public static class UserToken {
        public User user;
        public String token;
    }

    public UserToken login(String email, String password) {
        String hashedPassword = hashPassword(password);
        LOG.info("hashed password is={}", hashedPassword);
        User user = userService.getUser(email, hashedPassword);
        if (user == null) {
            LOG.info("User unable to login. user={}", email);
            return null;
        }
        LOG.info("User successfully logged in. user={}", email);


        String token = Jwts.builder().setSubject(email).setId(user.getId().toString()).signWith(SignatureAlgorithm.HS512, SECRET).compact();

        UserToken userToken = new UserToken();
        userToken.user = user;
        userToken.token = token;
        return userToken;
    }

    private String hashPassword(String password) {
        return DigestUtils.sha512Hex(salt + password);
    }
}
