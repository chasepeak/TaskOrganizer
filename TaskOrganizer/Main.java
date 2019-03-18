import java.util.ArrayList;
import java.util.Scanner;
import java.time.MonthDay;

public class Main {

    private static Workspace w = new Workspace();
    private static AssignmentParser parser = new AssignmentParser();

    public static void main(String[] args) {
        parser.loadWorkspace(w);
        w.printAll();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter: ");
        String input = scanner.nextLine();
        String[] commands = input.split(" ");

        while(!commands[0].equals("quit")) {
            try {
                if (commands[1].equals("course")) {//deal with courses
                    Course c = new Course(commands[2], Integer.parseInt(commands[3]));
                    if (commands[0].equals("add") && !w.getCourses().contains(c)) {
                        w.addCourse(c);
                    }
                    else if (commands[0].equals("remove") && w.getCourses().contains(c)) {
                        w.removeCourse(c);
                    }
                    else {
                        throw new Exception();
                    }
                }
                else if (commands[1].equals("assignment")) { //deal with assignments
                    if (commands[0].equals("add") && commands[3].equals("due")
                        && commands[5].equals("to")) {
                        Course c = new Course(commands[6], Integer.parseInt(commands[7]));
                        String[] fullDate = commands[4].split("-");
                        MonthDay date = MonthDay.of(Integer.parseInt(fullDate[0]),
                                        Integer.parseInt(fullDate[1]));
                        Assignment a = new Assignment(commands[2], date);
                        int j = w.getCourses().indexOf(c);

                        w.getCourses().get(j).addAssignment(a);
                    }
                    else if (commands[0].equals("remove") &&
                                commands[3].equals("from")) {
                        Assignment a = new Assignment(commands[2], null);
                        for (Course c: w.getCourses()) {
                            if (c.getAssignments().contains(a)) {
                                c.removeAssignment(a);
                            }
                        }
                    }
                    else {
                        throw new Exception();
                    }
                }
                else if (commands[0].equals("display")) { //display
                    switch(commands[1]){
                        case "courses":
                            w.printCourses();
                            break;
                        case "assignments":
                            w.printAssignments();
                            break;
                        case "all":
                            w.printAll();
                            break;
                        default:
                            throw new Exception();
                    }
                }
                else {
                    throw new Exception();
                }
            }
            catch (Exception e){
                usage();
            }
            finally {
                System.out.print("Enter: ");
                input = scanner.nextLine();
                commands = input.split(" ");
            }
        }
        parser.storeAssignmentInfo(w);
        System.out.println("Assignment data saved.");
    }

    private static void usage() {
        System.out.println();
        System.out.println("Format: <identifier> <item> ...");
        System.out.println();
        System.out.println("add    course <course name>");
        System.out.println("remove course <course name>");
        System.out.println("add    assignment <assignment name> due <date> to <course name>");
        System.out.println("remove assignment <assignment name> from <course name>");
        System.out.println("display <courses/assignments/all>");
        System.out.println("quit");
        System.out.println();
        System.out.println("Examples:");
        System.out.println("    add course MATH 452");
        System.out.println("    add assignment Homework5 due 03-13 to MATH 451");
        System.out.println("    display assignments");
    }
}
