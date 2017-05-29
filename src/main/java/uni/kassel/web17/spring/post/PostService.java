package uni.kassel.web17.spring.post;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class PostService {
	
	private List<String> posts = new LinkedList<>();

	public List<String> getPosts() {
//	posts.add(new Date().toString());
//	posts.add("post2");
		return posts;
	}
	
	public void addPostFromTitle(String str){
		posts.add(str);
	}
}

