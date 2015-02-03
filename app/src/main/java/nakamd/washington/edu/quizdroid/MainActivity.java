package nakamd.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Button> subjects = new ArrayList<Button>();
        subjects.add((Button) findViewById(R.id.button));
        subjects.add((Button) findViewById(R.id.button2));
        subjects.add((Button) findViewById(R.id.button3));

        for (Button subject : subjects) {
            final Button s = subject;
            subject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent nextActivity = new Intent(MainActivity.this, MainActivity2.class); // cannot use just this cuz this refers to the listener, not the outer this
                    nextActivity.putExtra("subject", s.getText());
                    // add data to be passed to next activity
                    nextActivity.putExtra("timestamp", new Date().toString());


                    if (nextActivity.resolveActivity(getPackageManager()) != null) {
                        startActivity(nextActivity); // opens a new activity
                    }
                    // code still runs asynchronously

                    finish(); // kill this instance self (this activity)
                }
            });
        }
    }

    private QuestionMain setQuestions(int id) {
        switch(id) {
            case R.id.button:
                QuestionMain math = new QuestionMain();
                ArrayList<String> mathW = new ArrayList<String>();
                mathW.add("5");
                mathW.add("2");
                mathW.add("8");
                math.makeQuestion("What is 2 + 2", "4", mathW);
                return math;
            case R.id.button2:
                QuestionMain physics = new QuestionMain();
                ArrayList<String> physicsW = new ArrayList<String>();
                physicsW.add("always at rest");
                physicsW.add("Equal and opposite reation");
                physicsW.add("momentum");
                physics.makeQuestion("What is newton's first law of physics", "once in motion, always in motino", physicsW);
                return physics;
            case R.id.button3:
                QuestionMain marvel = new QuestionMain();
                ArrayList<String> marvelW = new ArrayList<String>();
                marvelW.add("Tony Stark");
                marvelW.add("Chris Evans");
                marvelW.add("Chris Helmsworth");
                marvel.makeQuestion("Which actor plays iron man in the advengers", "Robert Downey Jr", marvelW);
                return marvel;
        }
        return null;
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
