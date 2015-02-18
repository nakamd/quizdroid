package nakamd.washington.edu.quizdroid;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by danielnakamura on 2/17/15.
 */
public class Quiz implements Serializable {
    private String question;
    private int answer;
    private ArrayList<String> choices;

    public Quiz(String question, int answer, ArrayList<String> choices) {
        this.question = question;
        this.choices = choices;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public int getAnswer() {
        return answer;
    }

    public String getAnswerString() {
        return choices.get(answer);
    }

    public ArrayList<String> getChoices() { return choices; }
}
