package franck.asso.activity;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import franck.asso.R;
import franck.asso.model.Member;
import franck.asso.model.MemberFilter;
import franck.asso.model.MemberListViewAdapter;

/**
 * Created by franc on 20/10/2015.
 */
public class MembersListActivity extends ListActivity {
    protected ListView listView;
    protected MemberListViewAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the BookLibraryActivity/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            this.getListItemsView(new MemberFilter(query));
        } else {
            this.getListItemsView(new MemberFilter());
        }
    }

    public List<Member> getMembers(MemberFilter memberFilter) {
        /*get members from database*/
        return new ArrayList<Member>();
    }

    public void getListItemsView(MemberFilter memberFilter) {
        listView = (ListView) findViewById(android.R.id.list);
        listAdapter = new MemberListViewAdapter(this, R.layout.memberinlist, this.getMembers(memberFilter));
        listView.setAdapter(listAdapter);
    }

    /**
     * Called when the user touches the go to home button
     */
    public void goToHomePage(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

}
