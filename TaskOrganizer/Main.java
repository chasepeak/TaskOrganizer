import java.util.ArrayList;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.time.MonthDay;

public class Main {
//make sure to use dueDate.getTime() to write to file

    private static Workspace w = new Workspace();
    private static File file = new File("assignment_repository.txt");

    public static void main(String[] args) {
        loadWorkspace();

        printAll();
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("User: ");
        String input = scanner.nextLine();
        String[] commands = input.split(" ");
        boolean quit = false;

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
                        usage();
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
                        usage();
                    }
                }
                else if (commands[0].equals("display")) { //display
                    if (commands[1].equals("courses")) {
                        printCourses();
                    }
                    else if (commands[1].equals("assignments")) {
                        printAssignments();
                    }
                    else if (commands[1].equals("all")) {
                        printAll();
                    }
                    else {
                        usage();
                    }
                }
                else if (commands[0].equals("quit")) {
                    break;
                }
                else {
                    usage();
                }
            }
            catch (Exception e){
                usage();
            }
            finally {
                System.out.print("User: ");
                input = scanner.nextLine();
                commands = input.split(" ");
            }
        }
        storeAssignmentInfo();
        System.out.println("Assignment data saved.");
    }

    private static void storeAssignmentInfo() {//write
        //store like ex) MATH 452 homework1 --4-1
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Course c: w.getCourses()) {
                String[] line = new String[3];
                line[0] = c.toString();
                String[] temp = line;
                for (Assignment a: c.getAssignments()) {
                    line[1] = a.getName();
                    line[2] = a.getDueDate().toString();
                    for (String s:line) {
                        bw.write(s + " ");
                    }
                    bw.newLine();
                    line = temp;
                }
            }
            bw.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadWorkspace() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" "); 
                String[] date = words[3].split("-");

                Course c = new Course(words[0], Integer.parseInt(words[1]));
                Assignment a = new Assignment(words[2],
                    MonthDay.of(Integer.parseInt(date[2]), Integer.parseInt(date[3])));
                if (w.getCourses().contains(c)) {
                    int i;
                    if ((i = w.getCourses().indexOf(c)) != -1) {
                        PriorityQueue<Assignment> assignments = w.getCourses().get(i)
                                .getAssignments();
                        assignments.add(a);
                        c.setAssignments(assignments);
                        w.getCourses().add(c);
                    }
                }
                else {
                    c.addAssignment(a);
                    w.addCourse(c);
                }
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printAssignments() {
        ArrayList<Assignment> allAssignments = new ArrayList<Assignment>();
        for (Course c: w.getCourses()) {
            allAssignments.addAll(c.getAssignments());
        }
        allAssignments.sort((Assignment x, Assignment y) ->
                            x.getDueDate().compareTo(y.getDueDate()));
        for (Assignment a: allAssignments) {
            System.out.println("    -" + a);
        }
    }

    private static void printCourses() {
        for (Course c: w.getCourses()) {
            System.out.println(c);
        }
    }

    private static void printAll() {
        for (Course c: w.getCourses()) {
            System.out.println(c + ":");
            for (Assignment a: c.getAssignments()) {
                System.out.println("    -" + a);
            }
        }
    }

    private static void usage() {
        System.out.println("");
        System.out.println("Format: <identifier> <item> ...");
        System.out.println("");
        System.out.println("add    course <specific name>");
        System.out.println("remove course <specific name>");
        System.out.println("add    assignment <specific name> due <date> to <course>");
        System.out.println("remove assignment <specific name> from <course>");
        System.out.println("display <courses/assignments/all>");
        System.out.println("quit");
        System.out.println("");
        System.out.println("Examples:");
        System.out.println("    add course MATH 452");
        System.out.println("    add assignment Homework5 due 03-13 to MATH 451");
        System.out.println("    display assignments");
        System.out.println("");
    }
}
