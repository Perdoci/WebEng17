package uni.kassel.web17.spring.post;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class PostService {
	
	private List<PostObj> posts = new LinkedList<>();
	private HashMap<String,Date> postTitles = new HashMap<>();
	private HashMap<String,Date> tempPostTitles = new HashMap<>();

	public HashMap<String, Date> getPosts() {
//	posts.add(new Date().toString());
//	posts.add("post2");
		tempPostTitles.clear();
		for (PostObj postObj : posts) {
			String title = postObj.getTitle();
			Date time =  postObj.getTime();
			postTitles.put(title, time);
		}
		tempPostTitles = postTitles;
		return tempPostTitles;
	}
	
	public PostObj getPostById(int id){
		PostObj post = null;
		boolean found = false;
		
		for (PostObj postObj : posts) {
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
		return post;
	}
	
	public void addPost(PostObj po){
		posts.add(po);
	}

	public String deletePostById(int id) {
		boolean found = false;
		String notify = "Error deleting post by id!";

		for (PostObj postObj : posts) {
			if(postObj.getId()== id){
				posts.remove(postObj);
				found = true;
				notify = "Post with id " + id + " deleted succesfully";
			}
		}
		if(found == false){
			notify =  "Post with id " + id + " not found";
		}
		return  notify;
	}
}

