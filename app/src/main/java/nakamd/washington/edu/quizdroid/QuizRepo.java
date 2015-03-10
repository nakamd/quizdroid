package nakamd.washington.edu.quizdroid;

import android.app.Application;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by danielnakamura on 2/16/15.
 */
public class QuizRepo extends Application implements TopicRepository {
    private Map<String, Topic> subjects;
    private static QuizRepo instance;

    public QuizRepo() {
        if(instance == null) {
            instance = this;
        } else {
            Log.e("QuizApp", "There is already a quizapp built");
            throw new RuntimeException("Multi App Exception");
        }
    }

    public ArrayList<Quiz> getQuestions(String subject) {
        return subjects.get(subject).getQuestions();
    }

    public ArrayList<Topic> getTopics() {
        Collection<Topic> temp = subjects.values();
        ArrayList<Topic> result = new ArrayList<Topic>();
        for (Topic t : temp) {
            result.add(t);
        }
        return result;
    }

    public Topic getTopic(String subject) {
        return subjects.get(subject);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("QuizApp", "getFilesDir() = " + getFilesDir().getAbsolutePath());
        FileInputStream fis = null;
        try
        {
            fis = openFileInput("questions.json");
            String json = readJSONFile(fis);
            JSONArray jsonTopics = new JSONArray(json);

            subjects = new HashMap<String, Topic>();
            for (int i = 0; i < jsonTopics.length(); i++)
            {
                JSONObject topic = jsonTopics.getJSONObject(i);
                subjects.put(loadTopic(topic));
            }
        }
        catch (JSONException jsonEx)
        {
            Log.e("QuizApp", "Exception in reading JSON file: " + jsonEx.getMessage());
            Log.wtf("QuizApp", jsonEx);
        }
        catch (IOException ioEx)
        {
            Log.e("QuizApp", "Exception in reading JSON file: " + ioEx.getMessage());
            Log.wtf("QuizApp", ioEx);
        }
        finally
        {
            try
            {
                if (fis != null)
                    fis.close();
            }
            catch (IOException ioEx)
            {
                // Not much we can do here....
            }
        }
        DownloadService.setServiceAlarm(this, true);
    }

    private String readJSONFile(FileInputStream fis) throws IOException
    {
        int size = fis.available();
        byte[] buffer = new byte[size];
        fis.read(buffer);

        return new String(buffer, "UTF-8");
    }

    private Topic loadTopic(JSONObject topic) throws JSONException {
        JSONArray qs = topic.getJSONArray("questions");
        List<Topic> questions = new ArrayList<Topic>();
        for (int j=0; j< qs.length(); j++)
        {
            Log.d("QuizApp", "Adding " + qs.getJSONObject(j).getString("text"));
            questions.add(loadQuestion(qs.getJSONObject(j)));
        }

        return new Topic(topic.getString("title"), topic.getString("desc"), topic.getString("desc"), questions);
    }

    private Topic loadQuestion(JSONObject q)
            throws JSONException
    {
        return new Topic(q.getString("text"),
                q.getJSONArray("answers").getString(0),
                q.getJSONArray("answers").getString(1),
                q.getJSONArray("answers").getString(2),
                q.getJSONArray("answers").getString(3),
                q.getInt("answer"));
    }

}
