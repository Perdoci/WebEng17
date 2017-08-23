package uni.kassel.web17.spring.post;

import org.hibernate.annotations.CreationTimestamp;
import uni.kassel.web17.spring.comment.Comment;
import uni.kassel.web17.spring.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(optional = false)
    private User author;

    @Column(length = 500)
    private String title;

    @CreationTimestamp
    private Date time;

    public Post(){

    }

    public Post(Integer id, User author, Date time, String title){

        this.id = id;
        this.author = author;
        this.time = time;
        this.title = title;
    }

    @OneToMany
    private List<Comment> comments;


    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment){
        comments.add(comment);
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

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getAuthor() {
        return author;
    }



}
