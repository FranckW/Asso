package franck.asso.model;

import java.util.Date;

/**
 * Created by franc on 15/10/2015.
 */
public class News {
    protected String title;
    protected String content;
    protected String date;
    protected Member author;

    public News(String title, String content, String date, Member author) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public Member getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }
}
