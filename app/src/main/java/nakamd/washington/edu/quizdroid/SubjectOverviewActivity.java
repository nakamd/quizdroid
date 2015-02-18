package nakamd.washington.edu.quizdroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;


public class SubjectOverviewActivity extends ActionBarActivity {

    private static Topic subject;
    private static QuizRepo subjects;
    private static int index;
    private static ArrayList<Quiz> questions;
    private static FragmentManager fragmentManager;
    private static int numCorrect;
    private static String prevAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        index = 0;
        numCorrect = 0;
        setContentView(R.layout.activity_main_activity2);
        // Get the  Intent that opened this activity
        Intent launchedMe = getIntent();

        subject = (Topic) launchedMe.getSerializableExtra("subject");

        subjects = new QuizRepo();
        Log.i("subject", subject.getTitle());
        questions = subject.getQuestions();
        Log.i("Subject", questions.size() + "");
        int questionNum = questions.size();

        fragmentManager = getFragmentManager();

        setFirstFragment(questionNum, subject.getTitle());

        QuizApp app = (QuizApp) getApplication();
        TopicRepository tr = app.getRepo();
        Topic math = tr.getTopic("Math");
        Topic physics = tr.getTopic("Physics");
        Topic marvel = tr.getTopic("Marvel Super Heros");

//        ((ImageView) findViewById(R.id.image1)).setImageResource(math.getImage());
//        ((ImageView) findViewById(R.id.image2)).setImageResource(physics.getImage());
//        ((ImageView) findViewById(R.id.image3)).setImageResource(marvel.getImage());


    }

    private void setFirstFragment(int questionNum, String subjectName) {
        Fragment fragment = new OverviewFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    public static void nextQuestion() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new QuestionFragment();

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        index++;
    }

    public static void addNumCorrect() {
        numCorrect++;
    }

    public static int getIndex() { return index; }

    public static int getNumCorrect() { return numCorrect; }

    public static Quiz getQuestion() {
        return questions.get(index - 1);
    }

    public static ArrayList<Quiz> getQuestionHandler() {
        return questions;
    }

    public static int handlerSize() { return questions.size(); }

    public static int getNumQuestion() {
        return questions.size();
    }

    public static String getSubject() {
        return subject.getTitle();
    }

    public static void setPrevAnswer(String s) {
        prevAnswer = s;
    }

    public static String getPrevAnswer() { return prevAnswer; }

    public static void checkAnswer() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new AnswerFragment();

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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

/*
    private ArrayList<Quiz> setQuestionMain(String subject) {
        ArrayList<Quiz> questions = new ArrayList<Quiz>();
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
*/
}
