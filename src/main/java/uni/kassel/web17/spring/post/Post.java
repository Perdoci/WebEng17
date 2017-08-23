package uni.kassel.web17.spring.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import uni.kassel.web17.spring.comment.Comment;
import uni.kassel.web17.spring.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;

    @ManyToOne(optional = false)
    private User author;

    @Column(length = 500)
    private String title;

    @CreationTimestamp
    private Date time;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public Post(){
        comments = new LinkedList<>();
    }

    public Post(Integer id, User author, Date time, String title){

        this.id = id;
        this.author = author;
        this.time = time;
        this.title = title;
        comments = new LinkedList<>();
    }



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


    @JsonIgnore  //post id cannot be given as parameter when creating new posts
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty
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
