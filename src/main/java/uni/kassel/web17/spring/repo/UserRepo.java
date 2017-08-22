package uni.kassel.web17.spring.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uni.kassel.web17.spring.user.User;

public interface UserRepo extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM User_ u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);
}
