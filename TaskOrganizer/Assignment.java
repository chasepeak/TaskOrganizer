import java.util.Objects;
import java.time.MonthDay;

public final class Assignment{

    private final String name;
    private MonthDay dueDate;

    public Assignment(String name, MonthDay dueDate) {
        this.name = name;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public MonthDay getDueDate() {
        return dueDate;
    }

    public void changeDueDate(MonthDay newDate) {
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
            return a.getName().equals(name); 
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
