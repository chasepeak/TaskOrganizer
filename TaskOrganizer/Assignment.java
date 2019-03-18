import java.util.Date;
import java.util.Objects;

public final class Assignment{

    private final String name;
    private Date dueDate;

    public Assignment(String name, Date dueDate) {
        this.name = name;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void changeDueDate(Date newDate) {
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
            return a.getName().equals(name) && 
                    a.getDueDate().equals(dueDate);
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dueDate);
    }
}
