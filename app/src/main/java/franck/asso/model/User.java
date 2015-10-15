package franck.asso.model;

/**
 * Created by franc on 15/10/2015.
 */
public class User {
    private static User ourInstance = new User();
    
    public static User getInstance() {
        return ourInstance;
    }
    
    private User() {
    }

    protected String login;
    protected String password;

    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
