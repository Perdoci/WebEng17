package uni.kassel.web17.spring.post;

import org.junit.Assert;
import org.junit.Test;
import springfox.documentation.spring.web.json.Json;
import uni.kassel.web17.spring.post.Post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FirstTest  {

    @Test
    public void testIt() throws IOException {

        URL url = new URL("http://localhost:8080/api/post/1");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        String s = content.toString();

        Assert.assertTrue(s.contains("michael"));

        in.close();
        con.disconnect();
    }

}
