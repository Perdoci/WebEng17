package uni.kassel.web17.spring.user;

import javax.persistence.*;

@Entity(name = "User_")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String email;
    private String password;


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
