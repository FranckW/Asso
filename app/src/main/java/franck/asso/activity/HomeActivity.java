package franck.asso.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import franck.asso.R;
import franck.asso.model.NewsList;
import franck.asso.model.NewsListViewAdapter;
import franck.asso.model.User;
import franck.asso.utils.ActivitiesHistory;

public class HomeActivity extends ListActivity {
    protected ListView listView;
    protected NewsListViewAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (User.getInstance().isSet()) {
            setContentView(R.layout.activity_home);
            this.displayNewsList();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeActivity/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }

    public void displayNewsList() {
        listView = (ListView) findViewById(R.id.newsList);
        listAdapter = new NewsListViewAdapter(this, R.layout.newsinlist, NewsList.getInstance().getNewsList());
        listView.setAdapter(listAdapter);
    }

    public void goToMembersList(View view) {
        Intent intent = new Intent(this, MembersListActivity.class);
        startActivity(intent);
    }

    public void logOut(View view) {
        User.getInstance().reset();
        ActivitiesHistory.getInstance().flush();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
    }
}
