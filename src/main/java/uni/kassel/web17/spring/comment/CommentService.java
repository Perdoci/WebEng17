package uni.kassel.web17.spring.comment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

        if(comment.getAuthor().equals(userService.getCurrentUser())){
            LOG.info("Deleting comment not allowed. user={}, id={}", userService.getCurrentUser().getEmail(), id);
            throw new IllegalStateException("User not allowed to delete comment");

        }
        LOG.info("Deleting comment. user={}, id={}", userService.getCurrentUser().getEmail(), id);
        postService.deleteComment(comment);

    }
}
