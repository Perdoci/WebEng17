package uni.kassel.web17.spring.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import uni.kassel.web17.spring.user.UserService;

import java.util.Iterator;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

//spring#s testing support for JUnit
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(PostServiceTest.class);


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

    //annotation transactional makes it possible to roll back
    //database changes after the test has finished
    //so that the next test starts without changes made
    //from the tests before
    @Test
    public void createPostTest(){
        LOG.info("Number of posts: {}", countPosts());
        Post post = new Post();
        post.setTitle("testPost");
        assertNull(post.getId());
        postService.addPost(post);
        assertNotNull(post.getId());
    }


    @Test
    @Transactional
    public void testPostPersisted() {
        LOG.info("Number of posts: {}", countPosts());
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

    private int countPosts() {
        int count = 0;
        Iterator<Post> it = postService.getAllPosts().iterator();
        while (it.hasNext()) {
            it.next();
            count++;
        }

        return count;
    }

}
