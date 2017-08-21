package uni.kassel.web17.spring.post;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//RestController searcher the class for annotations 
//defining particular endpoints 
@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	@RequestMapping("/post")
	public List<String> getPosts() {
		return postService.getPostTitles();
	}
  
	
	@RequestMapping("/post/add")
	public String addPost(@RequestParam("title") String str) {
		
		PostObj po = new PostObj();
		po.setTitle(str);
		po.setId(IdCounter.nextId());
		po.setTime(System.currentTimeMillis());
		
		postService.addPostFromTitle(po);
		return new String("You just added the Comment:" + str + " with the id " + po.getId());
	}
	
	@RequestMapping("/post/id")
	public String getPostById(@RequestParam("id") int id) {
		return postService.getPostById(id);
	}


}
