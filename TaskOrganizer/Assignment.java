import java.util.Objects;
import java.time.MonthDay;

public final class Assignment{

    private final String name;
    private AssignmentDate dueDate;

    public Assignment(String name, MonthDay dueDate) {
        this.name = name;
        this.dueDate = new AssignmentDate(dueDate);
    }

    public class AssignmentDate {
        private String day;
        private String month;

        public AssignmentDate(MonthDay date) {
            this.day = Integer.toString(date.getDayOfMonth());
            this.month = Integer.toString(date.getMonthValue());
        }

        public String getDay() {
            return day;
        }

        public String getMonth() {
            return month;
        }

        @Override
        public String toString() {
            return month + "/" + day;
        }

        public int compareTo(AssignmentDate date) {
            int result = month.compareTo(date.getMonth());
            //if the months are equal
            if (result == 0) {
                return day.compareTo(date.getDay());
            }
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof AssignmentDate) {
                AssignmentDate date = (AssignmentDate) other;
                return day.equals(date.getDay()) && month.equals(date.getMonth());
            }
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public AssignmentDate getDueDate() {
        return dueDate;
    }

    public void changeDueDate(AssignmentDate newDate) {
        dueDate = newDate;
    }

    @Override
    public String toString() {
        return name + " is due on " + dueDate;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Assignment) {
            Assignment a = (Assignment) other;
            return name.equals(a.getName()); 
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
