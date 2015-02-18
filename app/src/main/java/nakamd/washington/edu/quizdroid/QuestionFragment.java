package nakamd.washington.edu.quizdroid;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionFragment newInstance(String param1, String param2) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_question, container, false);
        Log.i("Question", "made it in Question Fragment");
        Quiz question = SubjectOverviewActivity.getQuestion();
        String prompt  = question.getQuestion();

        TextView questionPrompt = (TextView) rootView.findViewById(R.id.questionPrompt);
        questionPrompt.setText(prompt);

        setUpRadioButtons(rootView, question.getChoices(), question.getAnswer());

        Button button = (Button) rootView.findViewById(R.id.giveAnswer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get previous radio button answer
                RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radioGroup);
                int radioGroupID = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioGroupID);
                String prevAnswer = radioButton.getText().toString();
                SubjectOverviewActivity.setPrevAnswer(prevAnswer);
                String correctAnswer = SubjectOverviewActivity.getQuestion().getAnswerString();
                if (radioButton.getText().equals(SubjectOverviewActivity.getQuestion().getAnswer())) { // if correct
                    SubjectOverviewActivity.addNumCorrect();
                }

                SubjectOverviewActivity.checkAnswer(); // opens a new activity
            }
        });
        return rootView;
    }

    private void setUpRadioButtons(final View rootView, ArrayList<String> options, int answer) {
        RadioButton[] buttons = { (RadioButton)rootView.findViewById(R.id.a),
                (RadioButton)rootView.findViewById(R.id.b), (RadioButton)rootView.findViewById(R.id.c),
                (RadioButton)rootView.findViewById(R.id.d) };
/*

        int correctOption = (int)(Math.random() * 4);
        int count = 0;
*/
        for (int i = 0; i < buttons.length; i++) {
/*            String text = "";
            if (i == correctOption) {
                text = answer;
            } else {
                text = options.get(count);
                count++;
            }*/
            buttons[i].setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Button button = (Button) rootView.findViewById(R.id.giveAnswer);
                      button.setEnabled(true);
                  }
            });
            buttons[i].setText(options.get(i));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
