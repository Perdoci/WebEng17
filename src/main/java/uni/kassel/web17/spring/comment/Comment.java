package uni.kassel.web17.spring.comment;

import uni.kassel.web17.spring.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 60000)
    private String message;

    private Date time;

    @ManyToOne(optional = false)
    private User author;

    public Comment(){
        time = new Date(System.currentTimeMillis());
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author=" + author +
                ", text='" + message + '\'' +
                ", createdAt=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Comment comment = (Comment) o;
        return id != null ? id.equals(comment.id) : comment.id == null;
    }


    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
