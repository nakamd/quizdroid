package nakamd.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;


public class QuestionActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent launchedMe = getIntent();
        final ArrayList<Question> questions = (ArrayList<Question>) launchedMe.getSerializableExtra("questionhandler");
        final int index = (int) launchedMe.getIntExtra("index", 0);
        final int numCorrect = (int) launchedMe.getIntExtra("numCorrect", 0);
        Log.i("quiz", questions.get(index).getQuestion());
        Question question = questions.get(index);
        String prompt  = question.getQuestion();

        TextView questionPrompt = (TextView) findViewById(R.id.questionPrompt);
        questionPrompt.setText(prompt);

        setUpRadioButtons(question.getWrongs(), question.getAnswer());

        Button button = (Button) findViewById(R.id.giveAnswer);
/*        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        Log.i("RadioId", radioGroup.getCheckedRadioButtonId() + "");
        if (radioGroup.getCheckedRadioButtonId() == -1) { // not selected
            button.setEnabled(false);
        }*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(QuestionActivity.this, ResultActivity.class);

                // get previous radio button answer
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
                int radioGroupID = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioGroupID);
                nextActivity.putExtra("prevAnswer", radioButton.getText());
                nextActivity.putExtra("correctAnswer", questions.get(index).getAnswer());
                if (radioButton.getText().equals(questions.get(index).getAnswer())) { // if correct
                    nextActivity.putExtra("numCorrect", numCorrect + 1);
                } else {
                    nextActivity.putExtra("numCorrect", numCorrect);
                }

                nextActivity.putExtra("questionhandler", questions);
                nextActivity.putExtra("index", index + 1); // implicit

                if (nextActivity.resolveActivity(getPackageManager()) != null) {
                    Log.i("QuestionActivity", "Entering Result Activity");
                    startActivity(nextActivity); // opens a new activity
                }
                finish(); // kill this instance self (this activity)
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question, menu);
        return true;
    }

    public void radioOnClick(View view) {
        Button button = (Button) findViewById(R.id.giveAnswer);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        button.setEnabled(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }
        return false;
    }


    private void setUpRadioButtons(ArrayList<String> options, String answer) {
        RadioButton[] buttons = { (RadioButton)findViewById(R.id.a),
                (RadioButton)findViewById(R.id.b), (RadioButton)findViewById(R.id.c),
                (RadioButton)findViewById(R.id.d) };

        int correctOption = (int)(Math.random() * 4);
        int count = 0;
        for (int i = 0; i < buttons.length; i++) {
            String text = "";
            if (i == correctOption) {
                text = answer;
            } else {
                text = options.get(count);
                count++;
            }
            buttons[i].setText(text);
        }
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
