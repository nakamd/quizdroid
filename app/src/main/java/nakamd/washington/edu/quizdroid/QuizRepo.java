package nakamd.washington.edu.quizdroid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by danielnakamura on 2/16/15.
 */
public class QuizRepo implements TopicRepository {
    private Map<String, Topic> subjects;
    //private ArrayList<Question> handler;

    public QuizRepo() {
        subjects = new HashMap<String, Topic>();
        subjects.put("Math", setQuestionMain("math"));
        subjects.put("Physics", setQuestionMain("physics"));
        subjects.put("Marvel Super Heros", setQuestionMain("marvel"));
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

    private Topic setQuestionMain(String subject) {
        Topic topic;
        ArrayList<Quiz> questions = new ArrayList<Quiz>();
        if (subject.equalsIgnoreCase("math")) {
            questions.add(getMath1());
            questions.add(getMath2());
            questions.add(getMath3());
            topic = new Topic("Math", "math questions", "This is the math category, we will go through simple arithmetic", questions);
        } else if (subject.equalsIgnoreCase("physics")) {
            questions.add(getPhysics1());
            questions.add(getPhysics2());
            questions.add(getPhysics3());
            topic = new Topic("Physics", "physics questions", "This is the physics category, we will go through simple physics", questions);
        } else {
            questions.add(getMarvel1());
            questions.add(getMarvel2());
            questions.add(getMarvel3());
            topic = new Topic("Marvel", "marvel questions", "This is the marvel characters category, we will go through simple marvel characters", questions);
        }

        return topic;
    }

    private Quiz getMath1() {
        ArrayList<String> math = new ArrayList<String>();
        math.add("4");
        math.add("5");
        math.add("2");
        math.add("8");
        return new Quiz("What is 2 + 2", 0, math);
    }

    private Quiz getMath2() {
        ArrayList<String> math = new ArrayList<String>();
        math.add("5x");
        math.add("10");
        math.add("0");
        math.add("10x");
        return new Quiz("What is the derivative of 5x^2", 3, math);
    }

    private Quiz getMath3() {
        ArrayList<String> math = new ArrayList<String>();
        math.add("24");
        math.add("1.4142");
        math.add("2.1");
        math.add("0.41");
        return new Quiz("What is the sqrt of 2", 1, math);
    }

    private Quiz getPhysics1() {
        ArrayList<String> physics = new ArrayList<String>();
        physics.add("0 C");
        physics.add("90 C");
        physics.add("100 C");
        physics.add("-10 C");
        return new Quiz("At what temperature does water boil", 2, physics);
    }

    private Quiz getPhysics2() {
        ArrayList<String> physics = new ArrayList<String>();
        physics.add("a");
        physics.add("g");
        physics.add("s");
        physics.add("v");
        return new Quiz("What is the symbol for velocity", 3, physics);
    }

    private Quiz getPhysics3() {
        ArrayList<String> physics = new ArrayList<String>();
        physics.add("-9.81 m/s^2");
        physics.add("9.81 m/s^2");
        physics.add("-9.81 m/s");
        physics.add("9.81 m/s");
        return new Quiz("What is the acceleration of gravity", 0, physics);
    }

    private Quiz getMarvel1() {
        ArrayList<String> marvel = new ArrayList<String>();
        marvel.add("Hulk");
        marvel.add("X-Men");
        marvel.add("Captain America");
        marvel.add("Spider-Man");
        return new Quiz("What was the first Marvel movie", 2, marvel);
    }

    private Quiz getMarvel2() {
        ArrayList<String> marvel = new ArrayList<String>();
        marvel.add("The Avengers");
        marvel.add("Iron Man");
        marvel.add("Thor");
        marvel.add("Guardians of the Galaxy");
        return new Quiz("Which movie has no sequal", 3, marvel);
    }

    private Quiz getMarvel3() {
        ArrayList<String> marvel = new ArrayList<String>();
        marvel.add("2008");
        marvel.add("2007");
        marvel.add("2012");
        marvel.add("2013");
        return new Quiz("What year did Ghost Rider come out", 1, marvel);
    }
}
