package uni.kassel.web17.spring.post;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class PostService {
	
	private List<PostObj> posts = new LinkedList<>();
	private List<String> postTitles = new LinkedList<>();
	private List<String> tempPostTitles = new LinkedList<>();

	public List<String> getPostTitles() {
//	posts.add(new Date().toString());
//	posts.add("post2");
		tempPostTitles.clear();
		for (PostObj postObj : posts) {
			postTitles.add(postObj.getTitle());
		}
		tempPostTitles = postTitles;
		return tempPostTitles;
	}
	
	public String getPostById(int id){
		String post = null;
		boolean found = false;
		
		for (PostObj postObj : posts) {
			if(postObj.getId()== id){
				post = postObj.getTitle();
				found =true;
			}
		}
		if(found == false){
			return "No post found with that id";
		}else{

		return post;
		}
	}
	
	public void addPostFromTitle(PostObj po){
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

