package nakamd.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

import javax.security.auth.Subject;


public class HomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ArrayList<String> subjects = new ArrayList<String>();
        subjects.add("Math");
        subjects.add("Physics");
        subjects.add("Marvel Super Heros");

        QuizApp app = (QuizApp) getApplication();
        TopicRepository tr = app.getRepo();
        ArrayList<Topic> topics = tr.getTopics();

        final ListView lv = (ListView) findViewById(R.id.listView);
        TopicListAdapter adapter = new TopicListAdapter(this, R.layout.topic_list_row, topics);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nextActivity = new Intent(HomeActivity.this, SubjectOverviewActivity.class); // cannot use just this cuz this refers to the listener, not the outer this
                Topic selected = (Topic) (lv.getItemAtPosition(position));
                Log.i("test", selected.toString());
                nextActivity.putExtra("subject", selected);
                if (nextActivity.resolveActivity(getPackageManager()) != null) {
                    startActivity(nextActivity); // opens a new activity
                }

                finish(); // kill this instance self (this activity)
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(item.getItemId() == R.id.action_settings){
            Intent nextActivity = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(nextActivity);
        }
        return false;
    }
}
