package franck.asso.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by franc on 29/11/2015.
 */
public class ActivitiesHistory {
    private static ActivitiesHistory ourInstance = new ActivitiesHistory();
    protected List<Activity> runningActivities;

    public static ActivitiesHistory getInstance() {
        return ourInstance;
    }
    
    private ActivitiesHistory() {
        runningActivities = new ArrayList<Activity>();
    }

    public void flush() {
        runningActivities = new ArrayList<Activity>();
    }

    public void addActivity(Activity activity) {
        runningActivities.add(activity);
    }
}
