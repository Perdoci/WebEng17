package uni.kassel.web17.spring.post;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

//RestController searcher the class for annotations 
//defining particular endpoints 
@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	@RequestMapping(value = "/post", method = GET)
	public List<String> getPosts() {
		return postService.getPostTitles();
	}
  
	
	@RequestMapping(value = "/post/add", method = POST)
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
