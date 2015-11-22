package franck.asso.model;

/**
 * Created by franc on 20/10/2015.
 */
public class MemberFilter {
    protected String filter;

    public MemberFilter(String filter) {
        this.filter = filter;
    }

    public MemberFilter() {
        this.filter = "";
    }

    public boolean matches(Member member) {
        if (this.filter == null) return true;
        if (filter.equals("")) return true;
        if (member.getFirstName().contains(filter)) return true;
        if (member.getLastName().contains(filter)) return true;
        return false;
    }

    public String getFilter() {
        return filter;
    }
}
