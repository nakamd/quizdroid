package nakamd.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class ResultActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent launchedMe = getIntent();
        final ArrayList<Question> questions = (ArrayList<Question>) launchedMe.getSerializableExtra("questionhandler");
        final int index = (int) launchedMe.getIntExtra("index", 0);
        final int numCorrect = (int) launchedMe.getIntExtra("numCorrect", 0);
        String prevAnswer = (String) launchedMe.getStringExtra("prevAnswer");
        String correctAnswer = (String) launchedMe.getStringExtra("correctAnswer");

        TextView result = (TextView)findViewById(R.id.Result);
        if (prevAnswer.equals(correctAnswer))
            result.setText(getResources().getString(R.string.correct));
        else
            result.setText(getResources().getString(R.string.incorrect));

        TextView usersAnswer = (TextView)findViewById(R.id.userAnswer);
        usersAnswer.setText(getResources().getString(R.string.userAns) + prevAnswer);

        TextView corAns = (TextView)findViewById(R.id.correctAnswer);
        corAns.setText(getResources().getString(R.string.corAns) + correctAnswer);

        TextView totalCorrect = (TextView)findViewById(R.id.totalCorrect);
        totalCorrect.setText(getResources().getString(R.string.TotCor) + numCorrect + " out of " +
                questions.size());

        Button resultButton = (Button) findViewById(R.id.ResultButton);
        boolean isLast = false;
        Log.i("questionSize", questions.size() + "");
        Log.i("index", index + "");
        if (index >= questions.size())
            resultButton.setText("Finish");
        else
            resultButton.setText("Next Question");

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity;
                if (index >= questions.size()) {
                    nextActivity = new Intent(ResultActivity.this, HomeActivity.class);
                    Log.i("ResultActivity", "Entering HomeActivity");
                    startActivity(nextActivity);
                    finish();
                } else {
                    nextActivity = new Intent(ResultActivity.this, QuestionActivity.class);
                }
                nextActivity.putExtra("questionhandler", questions);
                nextActivity.putExtra("index", index);
                nextActivity.putExtra("numCorrect", numCorrect);

                if (nextActivity.resolveActivity(getPackageManager()) != null) {
                    Log.i("ResultActivity", "Entering question Activity");
                    startActivity(nextActivity); // opens a new activity
                }
                finish(); // kill this instance self (this activity)
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
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
