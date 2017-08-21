package uni.kassel.web17.spring.post;

import java.util.concurrent.atomic.AtomicInteger;

public class IdCounter {

   private static AtomicInteger id = new AtomicInteger();


    public static int nextId(){
        return id.incrementAndGet();
    }


}

