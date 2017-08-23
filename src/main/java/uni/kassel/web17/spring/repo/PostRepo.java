package uni.kassel.web17.spring.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uni.kassel.web17.spring.comment.Comment;
import uni.kassel.web17.spring.post.Post;

import java.util.List;


public interface PostRepo extends CrudRepository<Post, Integer> {

    //name of the entity should be the same as the class or else it crashes
    @Query( "select po from Post po order by po.time asc ")
    List<Post> findAll();

    //the order of the columns specified to select in the new object should be the same as in the
    //contructor of Post class.
    @Query( "select new Post(po.id, po.author, po.time, po.title) from Post po order by po.time asc ")
    List<Post> findAllWithoutComments();

    @Query( "select po from Post po where po.id = :id ")
    Post findOne(@Param("id")Integer integer);

    @Query( "delete from Post po where po.id = :id ")
    Post deletePostBy(@Param("id")Integer integer);

    @Query( "delete from Post po where po.id = :id ")
    Post deleteCommentById(Integer id);

    @Query( "select po from Post po where :comment member of po.comments")
    Post findPostForComment(@Param("comment") Comment comment);
}
