package franck.asso.model;

import java.util.Date;

/**
 * Created by franc on 15/10/2015.
 */
public class News {
    protected String content;
    protected Date date;

    public News(String content, Date date) {
        this.content = content;
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }
}
