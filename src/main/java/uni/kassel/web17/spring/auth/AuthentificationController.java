package uni.kassel.web17.spring.auth;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.kassel.web17.spring.user.User;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/user")
public class AuthentificationController {
    public static class UserLogin {
        public String email;
        public String password;

        @Override
        public String toString() {
            return "UserLogin{" +
                    "email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    public class UserToken{
        public User user;
        public String token;
    }


    @RequestMapping(value = "login", method = POST)
    public UserToken login(@RequestBody UserLogin userLogin) {
        UserToken token = new UserToken();
        token.user = new User();
        token.user.setEmail(userLogin.email);
        token.user.setPassword(userLogin.password);
        token.user.setId(101);
        token.token = "<JWT-TOKEN>";

        return  token;
    }
}
