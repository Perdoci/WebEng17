package uni.kassel.web17.spring.post;

import java.util.Date;

public class PostObj {
    private String title;
    private int id;
    private Date time;

    public PostObj(){
        id = IdCounter.nextId();
        time = new Date(System.currentTimeMillis());
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getTitle() {

        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }

    public void setTime(Date time) {

        this.time = time;
    }

    public Date getTime() {

        return time;
    }
}
