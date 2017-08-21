package uni.kassel.web17.spring.post;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

//RestController searcher the class for annotations 
//defining particular endpoints 
@RestController
public class PostController {

    @Autowired
    private PostService postService;


    @RequestMapping(value = "/post", method = GET)
    public HashMap<String,String> getPosts() {

        return postService.getPostTitles();
    }


    @RequestMapping(value = "/post/add", method = POST)
    public String addPost(@RequestParam("title") String str) {

        PostObj po = new PostObj();
        po.setTitle(str);
        po.setId(IdCounter.nextId());
        po.setTime(System.currentTimeMillis());

        postService.addPostFromTitle(po);
        return new String("You just added the Comment:" + str + " with id " + po.getId());
    }

    @RequestMapping(value = "/post/{id}", method = GET)
    public PostObj getPostById(@PathVariable int id) {

        return postService.getPostById(id);
    }

    @RequestMapping(value = "/post/{id}", method = DELETE)
    public String deletePostById(@PathVariable int id) {

        return postService.deletePostById(id);
    }


}
