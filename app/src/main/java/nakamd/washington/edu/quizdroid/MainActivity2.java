package nakamd.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;


public class MainActivity2 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        // Get the  Intent that opened this activity
        Intent launchedMe = getIntent();

        String timestamp = launchedMe.getStringExtra("timestamp");  // get data that was passed from first activity
        String subjectName = launchedMe.getStringExtra("subject");
        Log.i("test", subjectName);
        QuestionMain questionHandler = setQuestionMain(subjectName);
        Log.i("test", "questionMain set");
        int questionNum = questionHandler.size();
        Log.i("test", "" + questionNum);

        TextView number = (TextView) findViewById(R.id.questionNum);
        number.setText("" + number.getText() + questionNum);

        // add the extra data into the text view of the 2nd activity (this layout)
        TextView subjectTitle = (TextView) findViewById(R.id.subjectChosen);
        subjectTitle.setText(subjectName);

        TextView description = (TextView) findViewById(R.id.description);
        String descript = "";
        if (subjectName.equalsIgnoreCase("math"))
            descript = getResources().getString(R.string.math);
        else if (subjectName.equalsIgnoreCase("physics"))
            descript = getResources().getString(R.string.physics);
        else
            descript = getResources().getString(R.string.marvel);
        description.setText(descript);


        Button button = (Button) findViewById(R.id.begin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(MainActivity2.this, QuestionActivity.class); // cannot use just this cuz this refers to the listener, not the outer this
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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

    private QuestionMain setQuestionMain(String subject) {
        if (subject.equalsIgnoreCase("math")) {
            QuestionMain math = new QuestionMain();
            ArrayList<String> mathW = new ArrayList<String>();
            mathW.add("5");
            mathW.add("2");
            mathW.add("8");
            math.makeQuestion("What is 2 + 2", "4", mathW);
            return math;
        } else if (subject.equalsIgnoreCase("physics")) {
            QuestionMain physics = new QuestionMain();
            Log.i("test", "created QuestionMain object");
            ArrayList<String> physicsW = new ArrayList<String>();
            physicsW.add("always at rest");
            physicsW.add("Equal and opposite reation");
            physicsW.add("momentum");
            physics.makeQuestion("What is newtons first law of physics", "once in motion, always in motion", physicsW);
            return physics;
        } else {
            QuestionMain marvel = new QuestionMain();
            ArrayList<String> marvelW = new ArrayList<String>();
            marvelW.add("Tony Stark");
            marvelW.add("Chris Evans");
            marvelW.add("Chris Helmsworth");
            marvel.makeQuestion("Which actor plays iron man in the advengers", "Robert Downey Jr", marvelW);
            return marvel;
        }
    }
}
