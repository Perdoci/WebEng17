package uni.kassel.web17.spring.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uni.kassel.web17.spring.comment.Comment;

public interface CommentRepo extends CrudRepository<Comment, Integer> {

    @Query("select c from Comment c where c.id = :id ")
    Comment findCommentById(@Param("id") Integer id);

}
