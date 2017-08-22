package uni.kassel.web17.spring.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.kassel.web17.spring.user.User;
import uni.kassel.web17.spring.user.UserService;

@Service
public class AuthentificationService {

    @Autowired
    private UserService userService;

    private final String SECRET = "its actually not a secret";

    public Object parseToken(String jwtToken) {
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
        User user = userService.getUser(email, password);
        if (user == null) {
            return null;
        }


        String token = Jwts.builder().setSubject(email).setId(user.getEmail().toString()).signWith(SignatureAlgorithm.HS512, SECRET).compact();

        UserToken userToken = new UserToken();
        userToken.user = user;
        userToken.token = token;
        return userToken;
    }
}
