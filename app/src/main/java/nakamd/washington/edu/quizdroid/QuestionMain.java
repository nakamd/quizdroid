package nakamd.washington.edu.quizdroid;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by danielnakamura on 2/3/15.
 */
public class QuestionMain implements Serializable {
    private ArrayList<Question> questions;
    private int size;

    public QuestionMain() {
        questions = new ArrayList<Question>();
        size = 0;
    }

    public void makeQuestion(String question, String answer, ArrayList<String> wrongs) {
        Question temp = new Question(question, answer, wrongs);
        questions.add(temp);
        size++;
    }

    public int size() {
        return size;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }


    private class Question {
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

        public ArrayList<String> getWrongs() {
            int temp = (int) Math.round(Math.random() * wrongs.size());
            for (int i = 0; i < 3; i++) {
                wrongs.get((temp + i) % 3);
            }
            return wrongs;
        }
    }
}
