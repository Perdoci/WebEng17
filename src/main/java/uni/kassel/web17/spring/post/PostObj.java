package uni.kassel.web17.spring.post;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PostObj {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    @CreationTimestamp
    private Date time;

    public PostObj(){

    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getTitle() {

        return title;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {

        return id;
    }

    public void setTime(Date time) {

        this.time = time;
    }

    public Date getTime() {

        return time;
    }
}
