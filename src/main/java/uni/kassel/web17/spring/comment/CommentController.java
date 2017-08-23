package uni.kassel.web17.spring.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uni.kassel.web17.spring.post.Post;
import uni.kassel.web17.spring.post.PostService;
import uni.kassel.web17.spring.user.User;
import uni.kassel.web17.spring.user.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

public class CommentController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/api/comment", method = GET)
    public Iterable<Post> getPostList() {

        return null;
    }

    @RequestMapping(value = "/api/comment", method = POST)
    public ResponseEntity<Object> addComment(@RequestBody Post post) {
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/api/comment/{id}", method = GET)
    public ResponseEntity<Object> editComment(@PathVariable Long id, @RequestBody Comment comment) {
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/api/comment/{id}", method = DELETE)
    public void deleteComment(@PathVariable Integer id) {
        commentService.deleteCommentById(id);
    }
}
