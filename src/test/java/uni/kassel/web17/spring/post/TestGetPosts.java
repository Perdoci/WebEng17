package uni.kassel.web17.spring.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestGetPosts {

    private static final Logger LOG = LoggerFactory.getLogger(TestGetPosts.class);

    @LocalServerPort
    int port;


    @Test
    public void testGetPostsWithoutAuth() {
        RestTemplate rest = new RestTemplate();
        ResponseEntity<List> response = rest.getForEntity("http://localhost:" + port + "/api/post", List.class);
        List<Post> posts = response.getBody();
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(posts.size() == 0);
    }

}
