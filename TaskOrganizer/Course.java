import java.util.Objects;
import java.util.PriorityQueue;

public final class Course {

    private final String department;
    private final int courseNumber;
    private PriorityQueue<Assignment> assignments;

    public Course(String department, int courseNumber) {

        /**
         * Requirements:
         *  - department name must be a string that isuppercase and between
         *    2 and 4 characters
         *  - course number must be an integer between 100 and 599
         */

        assert department.toUpperCase().equals(department);
        assert department.length() > 1 && department.length() < 5;
        assert courseNumber > 100 && courseNumber < 600;

        this.department = department;
        this.courseNumber = courseNumber;
        this.assignments = new PriorityQueue<Assignment>(
         (Assignment x, Assignment y) -> {
         return x.getDueDate().compareTo(y.getDueDate());});
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public String getDepartment() {
        return department;
    }

    public PriorityQueue<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(PriorityQueue<Assignment> a) {
        assignments = a;
    }

    public void addAssignment(Assignment a) {
        assignments.add(a);
    }

    public boolean removeAssignment(Assignment a) {
        return assignments.remove(a);
    }

    @Override
    public String toString() {
        return department + " " + courseNumber;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Course) {
            Course c = (Course) other;
            return c.getCourseNumber() == courseNumber &&
                    c.getDepartment().equals(department);
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(department, courseNumber);
    }
}
