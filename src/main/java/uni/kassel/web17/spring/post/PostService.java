package uni.kassel.web17.spring.post;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class PostService {
	
	private List<PostObj> posts = new LinkedList<>();
	private List<String> postTitles = new LinkedList<>();

	public List<String> getPostTitles() {
//	posts.add(new Date().toString());
//	posts.add("post2");
		for (PostObj postObj : posts) {
			postTitles.add(postObj.getTitle());
		}
		return postTitles;
	}
	
	public String getPostById(int id){
		String post = null;
		
		for (PostObj postObj : posts) {
			if(postObj.getId()== id){
				post = postObj.getTitle();
			}
		}
		if(post.equals("null")){
			return "No post found with that id";
		}
		else 
		return post;
	}
	
	public void addPostFromTitle(PostObj po){
		posts.add(po);
	}
}

