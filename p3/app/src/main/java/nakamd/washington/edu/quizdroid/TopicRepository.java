package nakamd.washington.edu.quizdroid;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by danielnakamura on 2/16/15.
 */
public interface TopicRepository {
    public ArrayList<Quiz> getQuestions(String subject);
    public Topic getTopic(String subject);

}
