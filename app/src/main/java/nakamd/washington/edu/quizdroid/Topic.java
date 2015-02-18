package nakamd.washington.edu.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by danielnakamura on 2/17/15.
 * Topic is a title, short description, long description, and a collection of Question objects.
 */
public class Topic implements Serializable {
    private String title;
    private String shortDescript;
    private String longDescript;
    private ArrayList<Quiz> questions;
    private int image;

    public Topic(String title, String shortDescript, String longDescript, ArrayList<Quiz> questions) {
        this.title = title;
        this.shortDescript = shortDescript;
        this.longDescript = longDescript;
        this.questions = questions;
        this.image = R.drawable.ic_launcher;

    }

    public Topic(String title, String shortDescript, String longDescript, ArrayList<Quiz> questions, int imageResource) {
        this(title, shortDescript, longDescript, questions);
        this.image = imageResource;

    }


    public int getImage() {
        return image;
    }

    public String getTitle() { return title; }

    public String getShortDescript() { return shortDescript; }

    public String getLongDescript() { return longDescript; }

    public ArrayList<Quiz> getQuestions() { return questions; }
}