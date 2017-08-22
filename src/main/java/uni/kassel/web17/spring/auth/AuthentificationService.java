package uni.kassel.web17.spring.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.kassel.web17.spring.repo.UserRepo;
import uni.kassel.web17.spring.user.User;
import uni.kassel.web17.spring.user.UserService;

@Service
public class AuthentificationService {

    @Autowired
    private UserService userService;

    public static class UserToken {
        public User user;
        public String token;
    }

    public UserToken login(String email, String password) {
        User user = userService.getUser(email, password);
        if (user == null) {
            return null;
        }

        String secret = "its actually not a secret";
        String token = Jwts.builder().setSubject(email).signWith(SignatureAlgorithm.HS512, secret).compact();

        UserToken userToken = new UserToken();
        userToken.user = user;
        userToken.token = token;
        return userToken;
    }
}
