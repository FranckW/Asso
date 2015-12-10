package franck.asso.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by franc on 04/12/2015.
 */
public class NewsList {
    private static NewsList ourInstance = new NewsList();
    
    public static NewsList getInstance() {
        return ourInstance;
    }

    protected List<News> newsList;

    private NewsList() {
        /*Some examples, because we have not implemented news server side yet*/
        Member author1 = new Member(true, "Paulo", "Lasticot", "03/03/1980", "paulo@gmail.com", "10 rue blabla 59000 Lille", "0668531292");
        Member author2 = new Member(true, "Franck", "Warlouzet", "12/03/1992", "franck.warlouzet@hotmail.fr", "10 rue blabla 59000 Lille", "0668531292");
        News news1 = new News("Meeting in Lille !", "To show our faces to the people of Lille, we participate to a meeting in Lille.", "10/03/2016", author1);
        News news2 = new News("Christmas market", "In order to get more people, we will be at the Grand Place in Lille in the Christmas market.", "12/25/2015", author2);
        newsList = new ArrayList<News>();
        newsList.add(news1);
        newsList.add(news2);
    }

    public List<News> getNewsList() {
        return newsList;
    }
}
