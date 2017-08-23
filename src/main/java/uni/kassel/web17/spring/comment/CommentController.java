package uni.kassel.web17.spring.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.kassel.web17.spring.post.PostService;
import uni.kassel.web17.spring.user.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class CommentController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/api/comment/{id}", method = POST)
    public void editComment(@PathVariable Integer id, @RequestBody Comment comment) {
       commentService.editCommentByID(id, comment);
    }


    @RequestMapping(value = "/api/comment/{id}", method = DELETE)
    public void deleteComment(@PathVariable Integer id) {
        commentService.deleteCommentById(id);
    }
}
