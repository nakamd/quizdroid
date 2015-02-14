package nakamd.washington.edu.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AnswerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnswerFragment extends Fragment {
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
     * @return A new instance of fragment AnswerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AnswerFragment newInstance(String param1, String param2) {
        AnswerFragment fragment = new AnswerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AnswerFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_answer, container, false);
        Log.i("AnswerFragment", "Just Entered");

        String prevAnswer = SubjectOverviewActivity.getPrevAnswer();
        final int index = SubjectOverviewActivity.getIndex();
        Question question = SubjectOverviewActivity.getQuestion();
        String correctAnswer = question.getAnswer();
        int numCorrect = SubjectOverviewActivity.getNumCorrect();
        final int size = SubjectOverviewActivity.handlerSize();

        TextView result = (TextView)rootView.findViewById(R.id.Result);
        if (prevAnswer.equals(correctAnswer))
            result.setText(getResources().getString(R.string.correct));
        else
            result.setText(getResources().getString(R.string.incorrect));

        TextView usersAnswer = (TextView)rootView.findViewById(R.id.userAnswer);
        usersAnswer.setText(getResources().getString(R.string.userAns) + prevAnswer);

        TextView corAns = (TextView)rootView.findViewById(R.id.correctAnswer);
        corAns.setText(getResources().getString(R.string.corAns) + correctAnswer);

        TextView totalCorrect = (TextView)rootView.findViewById(R.id.totalCorrect);
        totalCorrect.setText(getResources().getString(R.string.TotCor) + numCorrect + " out of " +
                size);

        Button resultButton = (Button) rootView.findViewById(R.id.ResultButton);
        boolean isLast = false;
        Log.i("questionSize", size + "");
        Log.i("index", SubjectOverviewActivity.getIndex() + "");
        if (index >= size)
            resultButton.setText("Finish");
        else
            resultButton.setText("Next Question");

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index >= size) { // go to main activity
                    Intent nextActivity = new Intent(getActivity(), HomeActivity.class);
                    Log.i("ResultActivity", "Entering HomeActivity");
                    startActivity(nextActivity);
                    getActivity().finish();
                } else { // go to question fragment
                    SubjectOverviewActivity.nextQuestion();
                }
//                nextActivity.putExtra("questionhandler", questions);
//                nextActivity.putExtra("index", index);
//                nextActivity.putExtra("numCorrect", numCorrect);

/*
                if (nextActivity.resolveActivity(getPackageManager()) != null) {
                    Log.i("ResultActivity", "Entering question Activity");
                    startActivity(nextActivity); // opens a new activity
                }
                finish(); // kill this instance self (this activity)
*/
            }
        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
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
