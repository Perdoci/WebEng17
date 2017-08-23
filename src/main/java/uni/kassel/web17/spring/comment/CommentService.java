package uni.kassel.web17.spring.comment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.kassel.web17.spring.post.Post;
import uni.kassel.web17.spring.post.PostService;
import uni.kassel.web17.spring.repo.CommentRepo;
import uni.kassel.web17.spring.user.UserService;

@Service
public class CommentService {
    private static final Logger LOG = LoggerFactory.getLogger(CommentService.class);


    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    public void deleteCommentById(Integer id) {
        //search for the comment by id
        Comment comment = commentRepo.findCommentById(id);

        if(!comment.getAuthor().equals(userService.getCurrentUser())){
            LOG.info("Deleting comment not allowed. user={}, id={}", userService.getCurrentUser().getEmail(), id);
            throw new IllegalStateException("User not allowed to delete comment");

        }
        LOG.info("Deleting comment. user={}, id={}", userService.getCurrentUser().getEmail(), id);
        postService.deleteComment(comment);

    }

    public void editCommentByID(Integer id, Comment newComment) {

        //search for the comment by id
        Comment comment = commentRepo.findCommentById(id);

        if(!comment.getAuthor().equals(userService.getCurrentUser())){
            LOG.info("Editing comment not allowed. user={}, id={}", userService.getCurrentUser().getEmail(), id);
            throw new IllegalStateException("User not allowed to edit comment");

        }
        LOG.info("Editing comment. user={}, id={}", userService.getCurrentUser().getEmail(), id);
        comment.setMessage(newComment.getMessage());
        commentRepo.save(comment);
    }

    public void addCommentToPost(Integer id, String message) {
        LOG.debug("Trying to add comment to post. id={}", id);

        //create new Comment
        Comment newComment = new Comment();
        newComment.setMessage(message);
        newComment.setAuthor(userService.getCurrentUser());
        //save in the comment repo to generate id for commment
        commentRepo.save(newComment);
        postService.addCommentToPost(newComment, id);

    }
}
