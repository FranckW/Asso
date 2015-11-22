package franck.asso.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by franc on 12/11/2015.
 */
public class Utility {
    protected static Pattern pattern;
    protected static Matcher matcher;
    protected static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean validateEmail(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
