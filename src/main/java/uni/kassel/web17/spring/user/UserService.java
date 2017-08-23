package uni.kassel.web17.spring.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uni.kassel.web17.spring.repo.UserRepo;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepo userRepo;


    public User getCurrentUser() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUser;
    }

    public User getUser(String email, String password) {
        LOG.debug("Retrieving user from database. user={}", email);

        User user = userRepo.login(email, password);
        return user;
    }
}
