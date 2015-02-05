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


public class SubjectOverviewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        // Get the  Intent that opened this activity
        Intent launchedMe = getIntent();
        String timestamp = launchedMe.getStringExtra("timestamp");  // get data that was passed from first activity
        String subjectName = launchedMe.getStringExtra("subject");

        final ArrayList<Question> questionHandler = setQuestionMain(subjectName);
        int questionNum = questionHandler.size();

        setStatic(questionNum, subjectName);

        Button button = (Button) findViewById(R.id.begin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(SubjectOverviewActivity.this, QuestionActivity.class);
                nextActivity.putExtra("questionhandler", questionHandler);
//                nextActivity.putExtra("index", 0); // implicit
//                nextActivity.putExtra("numCorrect", 0);

                if (nextActivity.resolveActivity(getPackageManager()) != null) {
                    Log.i("test", "working");
                    startActivity(nextActivity); // opens a new activity
                }
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

    private ArrayList<Question> setQuestionMain(String subject) {
        ArrayList<Question> questions = new ArrayList<Question>();
        if (subject.equalsIgnoreCase("math")) {
            questions.add(getMath1());
            questions.add(getMath2());
            questions.add(getMath3());
        } else if (subject.equalsIgnoreCase("physics")) {
            questions.add(getPhysics1());
            questions.add(getPhysics2());
            questions.add(getPhysics3());
        } else {
            questions.add(getMarvel1());
            questions.add(getMarvel2());
            questions.add(getMarvel3());
        }
        return questions;
    }

    private void setStatic(int questionNum, String subjectName) {
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
    }


    private Question getMath1() {
        ArrayList<String> math = new ArrayList<String>();
        math.add("5");
        math.add("2");
        math.add("8");
        return new Question("What is 2 + 2", "4", math);
    }

    private Question getMath2() {
        ArrayList<String> math = new ArrayList<String>();
        math.add("5x");
        math.add("10");
        math.add("0");
        return new Question("What is the derivative of 5x^2", "10x", math);
    }

    private Question getMath3() {
        ArrayList<String> math = new ArrayList<String>();
        math.add("24");
        math.add("2.1");
        math.add("0.41");
        return new Question("What is the sqrt of 2", "1.4142", math);
    }

    private Question getPhysics1() {
        ArrayList<String> physics = new ArrayList<String>();
        physics.add("0 C");
        physics.add("90 C");
        physics.add("-10 C");
        return new Question("At what temperature does water boil", "100 C", physics);
    }

    private Question getPhysics2() {
        ArrayList<String> physics = new ArrayList<String>();
        physics.add("a");
        physics.add("g");
        physics.add("s");
        return new Question("What is the symbol for velocity", "v", physics);
    }

    private Question getPhysics3() {
        ArrayList<String> physics = new ArrayList<String>();
        physics.add("9.81 m/s^2");
        physics.add("-9.81 m/s");
        physics.add("9.81 m/s");
        return new Question("What is the acceleration of gravity", "-9.81 m/s^2", physics);
    }

    private Question getMarvel1() {
        ArrayList<String> marvel = new ArrayList<String>();
        marvel.add("Hulk");
        marvel.add("X-Men");
        marvel.add("Spider-Man");
        return new Question("What was the first Marvel movie", "Captain America", marvel);
    }

    private Question getMarvel2() {
        ArrayList<String> marvel = new ArrayList<String>();
        marvel.add("The Avengers");
        marvel.add("Iron Man");
        marvel.add("Thor");
        return new Question("Which movie has no sequal", "Guardians of the Galaxy", marvel);
    }

    private Question getMarvel3() {
        ArrayList<String> marvel = new ArrayList<String>();
        marvel.add("2008");
        marvel.add("2012");
        marvel.add("2013");
        return new Question("What year did Ghost Rider come out", "2007", marvel);
    }
}
