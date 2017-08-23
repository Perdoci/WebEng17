package uni.kassel.web17.spring.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.kassel.web17.spring.repo.PostRepo;
import uni.kassel.web17.spring.repo.UserRepo;
import uni.kassel.web17.spring.user.User;
import uni.kassel.web17.spring.user.UserService;

@Service
public class PostService {
	private static final Logger LOG = LoggerFactory.getLogger(PostService.class);

	@Autowired
	private PostRepo postRepo;
    @Autowired
    private UserService userService;

	public Iterable<PostObj> getPosts() {
		User currentUser = userService.getCurrentUser();
		LOG.info("Returning posts. user={}", userService.getCurrentUser().getEmail());
		return postRepo.findAll();
	}

	public PostObj getPostById(int id){
		LOG.info("Retrieving post. user={}, id={}", userService.getCurrentUser().getEmail(), id);


		return postRepo.findOne(id);
	}

    /*TODO add post to the local database*/
	public void addPost(PostObj po){
		LOG.info("Adding post. user={}, title={}", userService.getCurrentUser().getEmail(), po.getTitle());

       /* User user = userRepo.findByEmail("mlesniak@micromata.de");
        po.setAuthor(user);*/

        //posts need a user as a reason of stating optional=false at the relation declaration
        po.setAuthor(userService.getCurrentUser());
        postRepo.save(po);
	}

	public void deletePostById(int id) {
		// Validate that user is allowed to delete post.
		PostObj post = postRepo.findOne(id);
		if (!post.getAuthor().equals(userService.getCurrentUser())) {
			LOG.info("Deleting post not allowed. user={}, id={}", userService.getCurrentUser().getEmail(), id);
			throw new IllegalStateException("User not allowed to delete post");
		}

		LOG.info("Deleting post. user={}, id={}", userService.getCurrentUser().getEmail(), id);

		postRepo.delete(id);
	}
}

