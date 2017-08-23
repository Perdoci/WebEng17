package uni.kassel.web17.spring.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    //add comment to post
    @RequestMapping(value = "/api/{id}/comment", method = POST)
    public void addCommentToPost(@PathVariable Integer id, @RequestBody NewComment message) {
        commentService.addCommentToPost(id, message.getMessage());
    }


    public static class NewComment {
        private String message;

        public String getMessage() {
            return message;
        }
    }

}
