package franck.asso.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by franc on 28/11/2015.
 */
public class Members {
    private static Members ourInstance = new Members();
    
    public static Members getInstance() {
        return ourInstance;
    }

    protected List<Member> members;

    private Members() {
        members = new ArrayList<Member>();
    }

    public void addMembers(Member member) {
        members.add(member);
    }

    public List<Member> getMembers() {
        return members;
    }
}
