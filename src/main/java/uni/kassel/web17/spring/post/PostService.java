package uni.kassel.web17.spring.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class PostService {
	@Autowired
	private PostRepo postRepo;

	private HttpServletRequest request;

	public Iterable<PostObj> getPosts() {
	/*	tempPostTitles.clear();
		for (PostObj postObj : posts) {
			String title = postObj.getTitle();
			Date time =  postObj.getTime();
			postTitles.put(title, time);
		}
		tempPostTitles = postTitles;*/
		return postRepo.findAll();
	}

	public PostObj getPostById(int id){
	/*	PostObj post = null;
		boolean found = false;

		Iterable<PostObj> all = postRepo.findAll();
		for (PostObj postObj : all) {
			if(postObj.getId()== id){
				found =true;
				post = postObj;
			}
		}
		if(found == false){
			PostObj postObj = new PostObj();
			postObj.setTitle("No post found with the id " + id);
			post = postObj;

		}
		return post;*/
		return postRepo.findOne(id);
	}

    /*TODO add post to the local database*/
	public void addPost(PostObj po){
		postRepo.save(po);
	}

	public void deletePostById(int id) {
		/*boolean found = false;
		String notify = "Error deleting post by id!";
		Iterable<PostObj> all = postRepo.findAll();
		for (PostObj postObj : all) {
			if(postObj.getId()== id){
				postRepo.delete(postObj);
				found = true;
				notify = "Post with id " + id + " deleted succesfully";
			}
		}
		if(found == false){
			notify =  "Post with id " + id + " not found";
		}
		return  notify;*/
		postRepo.delete(id);
	}
}

