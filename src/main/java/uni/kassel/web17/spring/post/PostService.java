package uni.kassel.web17.spring.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.kassel.web17.spring.comment.Comment;
import uni.kassel.web17.spring.repo.CommentRepo;
import uni.kassel.web17.spring.repo.PostRepo;
import uni.kassel.web17.spring.user.User;
import uni.kassel.web17.spring.user.UserService;

@Service
public class PostService {
	private static final Logger LOG = LoggerFactory.getLogger(PostService.class);

	@Autowired
	private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private UserService userService;

	public Iterable<Post> getAllPosts() {
		User currentUser = userService.getCurrentUser();
		LOG.info("Returning posts. user={}", currentUser.getEmail());
		return postRepo.findAll();
	}
	public Iterable<Post> getPostsWithoutComments() {
		User currentUser = userService.getCurrentUser();
		LOG.info("Returning posts. user={}", currentUser.getEmail());
		return postRepo.findAllWithoutComments();
	}

	public Post getPostById(int id){
		LOG.info("Retrieving post. user={}, id={}", userService.getCurrentUser().getEmail(), id);


		return postRepo.findOne(id);
	}

    /*TODO add post to the local database*/
	public void addPost(Post po){
		LOG.info("Adding post. user={}, title={}", userService.getCurrentUser().getEmail(), po.getTitle());

        for (Comment comment: po.getComments()) {
            comment.setAuthor(userService.getCurrentUser());
        }

        //posts need a user as a reason of stating optional=false at the relation declaration
        po.setAuthor(userService.getCurrentUser());
        postRepo.save(po);
	}

	public void deletePostById(int id) {
		// Validate that user is allowed to delete post.
		Post post = postRepo.findOne(id);
		if (!post.getAuthor().equals(userService.getCurrentUser())) {
			LOG.info("Deleting post not allowed. user={}, id={}", userService.getCurrentUser().getEmail(), id);
			throw new IllegalStateException("User not allowed to delete post");
		}

		LOG.info("Deleting post. user={}, id={}", userService.getCurrentUser().getEmail(), id);

		postRepo.deletePostBy(id);
	}

	public void deleteCommentById(Integer id) {

	}

	public void deleteComment(Comment comment) {
		LOG.debug("Trying to remove comment. id={}", comment.getId());
		Post post = postRepo.findPostForComment(comment);
		if (post == null) {
			throw new IllegalArgumentException("No post found for comment");
		}
		post.getComments().remove(comment);
		postRepo.save(post);
	}

    public void addCommentToPost(Comment newComment, Integer id) {
        Post post = postRepo.findOne(id);
        if (post == null) {
            throw new IllegalArgumentException("No post found for comment");
        }
        post.getComments().add(newComment);
        postRepo.save(post);
    }
}

