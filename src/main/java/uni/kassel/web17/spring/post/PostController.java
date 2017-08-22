package uni.kassel.web17.spring.post;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public Iterable<PostObj> getPosts() {

        return postService.getPosts();
    }

    /**
     *
     * @param post object that gets created using
     *            the constructor through mapping attributes
     *             with the params in the request
     * @return notification message
     */
    @RequestMapping(value = "/post/add", method = POST)
    public String addPost(@RequestBody PostObj post) {
        postService.addPost(post);
        return "http://127.0.0.1:8080/post/" + post.getId();
    }

    @RequestMapping(value = "/post/{id}", method = GET)
    public PostObj getPostById(@PathVariable int id) {

        return postService.getPostById(id);
    }

    @RequestMapping(value = "/post/{id}", method = DELETE)
    public void deletePostById(@PathVariable int id) {

         postService.deletePostById(id);
    }


}
