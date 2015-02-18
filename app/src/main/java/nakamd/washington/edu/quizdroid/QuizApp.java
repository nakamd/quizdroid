package nakamd.washington.edu.quizdroid;

import android.app.Application;
import android.util.Log;

/**
 * Created by danielnakamura on 2/16/15.
 */
public class QuizApp extends Application{
    private static QuizApp instance;
    private TopicRepository topics;

    public QuizApp() {
        if(instance == null) {
            instance = this;
        } else {
            Log.e("QuizApp", "There is already a quizapp built");
            throw new RuntimeException("Already an instance created of app");
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("QuizApp", "onCreate is working");

        topics = new QuizRepo();
    }

    public static QuizApp getInstance() {
        return instance;
    }

    public TopicRepository getRepo() {
        return topics;
    }

}
