package uni.kassel.web17.spring.post;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "post")
public class PostObj {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;

    private String title;

    @CreationTimestamp
    @Column(name="time")
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
