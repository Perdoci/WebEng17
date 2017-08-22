package uni.kassel.web17.spring.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.kassel.web17.spring.repo.PostRepo;
import uni.kassel.web17.spring.repo.UserRepo;
import uni.kassel.web17.spring.user.User;

@Service
public class PostService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserRepo userRepo;



	public Iterable<PostObj> getPosts() {

		return postRepo.findAll();
	}

	public PostObj getPostById(int id){

		return postRepo.findOne(id);
	}

    /*TODO add post to the local database*/
	public void addPost(PostObj po){

		User author = userRepo.findByEmail("mlesniak@micromata.de");
		po.setAuthor(author);
		postRepo.save(po);
	}

	public void deletePostById(int id) {

		postRepo.delete(id);
	}
}

