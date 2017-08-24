package uni.kassel.web17.spring.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestAddPost{

    private static final Logger LOG = LoggerFactory.getLogger(TestGetPosts.class);

    @LocalServerPort
    int port;

    @Test
    public void testAddPostWithAuthentication() {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtaWNoYWVsIiwianRpIjoiMSJ9.wMUUv-C6GwDFZs8Om2st1id-PsYJ6PAUb3rRfcNlzNCOKaXk9J5t7BWLzmqrvDYlAMR9mTVEl42T9xL6atLVAA");
        Post post = new Post();
        String title = "123test";
        post.setTitle(title);
        HttpEntity<Post> entity = new HttpEntity<>(post, headers);
        ResponseEntity<String> response = rest.postForEntity("http://localhost:" + port + "/api/post", entity, String.class);

        String url = (String) response.getBody();
        Post storedPost = rest.getForObject(url, Post.class);
        assertEquals(title, storedPost.getTitle());
        assertEquals("michael", storedPost.getAuthor().getEmail());

    }
}
