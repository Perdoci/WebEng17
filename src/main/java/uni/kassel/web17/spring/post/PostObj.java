package uni.kassel.web17.spring.post;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class PostObj {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private Date time;

    public PostObj(){
        id = IdCounter.nextId();
        time = new Date(System.currentTimeMillis());
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getTitle() {

        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }

    public void setTime(Date time) {

        this.time = time;
    }

    public Date getTime() {

        return time;
    }
}
