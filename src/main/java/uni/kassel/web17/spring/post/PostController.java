package uni.kassel.web17.spring.post;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@RequestMapping("/post")
	public List<String> getPosts() {
		return postService.getPosts();
	}
  
	
	@RequestMapping("/post/add")
	public void addPost(@RequestParam("title") String str) {
		postService.addPostFromTitle(str);
	}


}
