package uni.kassel.web17.spring;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uni.kassel.web17.spring.post.Post;
import uni.kassel.web17.spring.post.PostService;
import uni.kassel.web17.spring.user.UserService;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

//spring#s testing support for JUnit
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    //create new user and set it current before creating post as
    //it will fire a null pointer exception cause creating a post
    //needs authentication from user side
    @Before
    public void setup() {
        userService.setCurrentUser(1, "michael");
    }

    @Test
    public void postServiceInstanceNotNull() {
        assertNotNull("The post instance is null.", postService);
    }

    @Test
    public void createPostTest(){
        Post post = new Post();
        post.setTitle("testPost");
        assertNull(post.getId());
        postService.addPost(post);
        assertNotNull(post.getId());
    }

    @Test
    public void testPostPersisted() {
        String uuid = UUID.randomUUID().toString();

        Post post = new Post();
        post.setTitle(uuid);

        assertNull(post.getId());
        postService.addPost(post);
        assertNotNull(post.getId());
        Integer id = post.getId();

        Post storedPost = postService.getPostById(id);
        assertEquals("Post correctly stored", storedPost.getTitle(), uuid);
    }


}
