package uni.kassel.web17.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.kassel.web17.spring.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public User getCurrentUser() {
        return userRepo.findByEmail("cele@yahoo.com");
    }

    public User getUser(String email, String password) {
        User user = userRepo.login(email, password);
        return user;
    }
}
