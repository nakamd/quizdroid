package nakamd.washington.edu.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by danielnakamura on 2/4/15.
 */
public class Question implements Serializable {
    private String question;
    private String answer;
    private ArrayList<String> wrongs;

    public Question(String question, String answer, ArrayList<String> wrongs) {
        this.question = question;
        this.wrongs = wrongs;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public ArrayList<String> getWrongs() {
        int temp = (int)(Math.random() * wrongs.size());
        for (int i = 0; i < 3; i++) {
            wrongs.get((temp + i) % 3);
        }
        return wrongs;
    }
}
