import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Workspace {
    //consider other data structures (hash set maybe)
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

    public void printCourses() {
        for (Course c: getCourses()) {
            System.out.println(c);
        }
    }

    public void printAssignments() {
        ArrayList<Assignment> allAssignments = new ArrayList<Assignment>();
        for (Course c: getCourses()) {
            allAssignments.addAll(c.getAssignments());
        }
        allAssignments.sort((Assignment x, Assignment y) ->
                            x.getDueDate().compareTo(y.getDueDate()));
        for (Assignment a: allAssignments) {
            System.out.println("    -" + a);
        }
    }

    public void printAll() {
        for (Course c: getCourses()) {
            System.out.println(c + ":");
            for (Assignment a: c.getAssignments()) {
                System.out.println("    -" + a);
            }
        }
    }

    
}
