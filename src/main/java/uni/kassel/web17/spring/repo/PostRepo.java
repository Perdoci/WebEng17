package uni.kassel.web17.spring.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uni.kassel.web17.spring.post.PostObj;

import java.util.List;


public interface PostRepo extends CrudRepository<PostObj, Integer> {

    //name of the entity should be the same as the class or else it crashes
    @Query( "select po from PostObj po order by po.time asc ")
    List<PostObj> findAll();

    @Query( "select po from PostObj po where po.id = :id ")
    PostObj findOne(@Param("id")Integer integer);
}
