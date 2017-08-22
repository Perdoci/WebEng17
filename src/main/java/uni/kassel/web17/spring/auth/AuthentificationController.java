package uni.kassel.web17.spring.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.kassel.web17.spring.repo.UserRepo;
import uni.kassel.web17.spring.user.User;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/user")
public class AuthentificationController {

    @Autowired
    private AuthentificationService authService;

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


    @RequestMapping(value = "login", method = POST)
    public AuthentificationService.UserToken login(@RequestBody UserLogin userLogin) {
        // Test, that retrieval of user works.
        AuthentificationService.UserToken token = authService.login(userLogin.email, userLogin.password);


        return token;
    }
}
