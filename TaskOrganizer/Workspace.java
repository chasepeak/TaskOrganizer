import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Workspace {

    private ArrayList<Course> courses;

    public Workspace() {
        this.courses = new ArrayList<Course>();
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course c) {
        courses.add(c);
    }

    public boolean removeCourse(Course c) {
        return courses.remove(c);
    }

}
