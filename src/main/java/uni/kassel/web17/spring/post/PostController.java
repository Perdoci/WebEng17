package uni.kassel.web17.spring.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.kassel.web17.spring.user.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

//RestController searcher the class for annotations 
//defining particular endpoints 
@RestController
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/api/post/all", method = GET)
    public Iterable<Post> getAllPosts() {

        return postService.getAllPosts();
    }

    @RequestMapping(value = "/api/post", method = GET)
    public Iterable<Post> getMyPosts() {

        return postService.getPostsWithoutComments();
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
        // A pragmatic approach to security which does not use much framework-specific magic. While other approaches
        // with annotations, etc. are possible they are much more complex while this is quite easy to understand and
        // extend.
        if (userService.isAnonymous()) {
            return "Not allowed to add post. You must be logged in to post something.";
        }
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
