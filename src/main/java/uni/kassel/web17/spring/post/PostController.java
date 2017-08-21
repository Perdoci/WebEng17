package uni.kassel.web17.spring.post;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public HashMap<String,Date> getPosts() {

        return postService.getPosts();
    }


    @RequestMapping(value = "/post/add", method = POST)
    public String addPost(@RequestBody PostObj post) {
        postService.addPost(post);
        return new String("You just added a new post.\n");
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
