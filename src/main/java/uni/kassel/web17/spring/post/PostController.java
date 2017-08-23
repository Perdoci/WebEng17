package uni.kassel.web17.spring.post;

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


    @RequestMapping(value = "/api/post", method = GET)
    public Iterable<Post> getPosts() {

        return postService.getPosts();
    }

    /**
     *
     * @param post object that gets created using
     *            the constructor through mapping attributes
     *             with the params in the request
     * @return notification message
     */
    @RequestMapping(value = "/api/post/add", method = POST)
    public String addPost(@RequestBody Post post) {
        postService.addPost(post);
        return "http://127.0.0.1:8080/post/" + post.getId();
    }

    @RequestMapping(value = "/api/post/{id}", method = GET)
    public Post getPostById(@PathVariable int id) {

        return postService.getPostById(id);
    }

    @RequestMapping(value = "/api/post/{id}", method = DELETE)
    public void deletePostById(@PathVariable int id) {

         postService.deletePostById(id);
    }


}
