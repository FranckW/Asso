package franck.asso.activity;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import franck.asso.R;
import franck.asso.model.Member;
import franck.asso.model.MemberFilter;
import franck.asso.model.MemberListViewAdapter;
import franck.asso.model.Members;
import franck.asso.utils.ActivitiesHistory;

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
        ActivitiesHistory.getInstance().addActivity(this);
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

    public void getMembers(String query) {
        RequestQueue queue = Volley.newRequestQueue(this);
        if (query.equals("")) {
            query = "*";
        }
        String url = "http://appli.yourtribes-soft.com:1701/membersFiltered/" + query;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String genderString = jsonObject.getString("gender");
                                boolean gender;
                                if (genderString.equals("male")) {
                                    gender = true;
                                } else {
                                    gender = false;
                                }
                                String firstName = jsonObject.getString("firstname");
                                String lastName = jsonObject.getString("lastname");
                                String birthDate = jsonObject.getString("birthday");
                                String email = jsonObject.getString("email");
                                String address = jsonObject.getString("phoneNumber");
                                String phoneNumber = jsonObject.getString("address");
                                Member member = new Member(gender, firstName, lastName, birthDate, email, phoneNumber, address);
                                Members.getInstance().addMembers(member);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error while processing request to get members, check your network", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
    }

    public void getListItemsView(MemberFilter memberFilter) {
        listView = (ListView) findViewById(android.R.id.list);
        Members.getInstance().getMembers().clear();
        this.getMembers(memberFilter.getFilter());
        listAdapter = new MemberListViewAdapter(this, R.layout.memberinlist, Members.getInstance().getMembers());
        listView.setAdapter(listAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, MemberActivity.class);
        intent.putExtra("member", Members.getInstance().getMembers().get(position));
        startActivity(intent);
    }

    /**
     * Called when the user touches the go to home button
     */
    public void goToHomePage(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
    }

}
