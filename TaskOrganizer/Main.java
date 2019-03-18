import java.util.ArrayList;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.Date;

public class Main {
//make sure to use dueDate.getTime() to write to file

    private static Workspace w = new Workspace();

    public static void main(String[] args) {
        loadWorkspace();

        printCourses();
        System.out.println();
        System.out.println();
        printAssignments();

        Scanner scanner = new Scanner(System.in);
        System.out.print("User: ");
        String input = scanner.nextLine();
        String[] commands = input.split(" ");

    }

    private static void printAssignments() {//information about 
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

    private static void usage() {//use for command line argument -help
        System.out.println("");
        System.out.println("Format: <identifier> <item> ...");
        System.out.println("");
        System.out.println("add    <course> <specific name>");
        System.out.println("remove <course> <specific name>");
        System.out.println("add    <assignment> <specific name> to <course>");
        System.out.println("remove <assignment> <specific name> from <course>");
        System.out.println("display <courses/assignments>");
        System.out.println("quit");
        System.out.println("");
        System.out.println("Examples:");
        System.out.println("    add course MATH 452");
        System.out.println("    add assignment Homework1 to MATH 452");
        System.out.println("    display assignments");
        System.out.println("");
    }

    private static void loadWorkspace() {
        File file = new File("assignment_repository.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" "); //cheap way out
                Course c = new Course(words[0], Integer.parseInt(words[1]));
                Assignment a = new Assignment(words[2], new Date(Integer.parseInt(words[3])));
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
/*
        while (!commands[0].equals("quit")) {
            try {
                switch(commands[0]) {
                    case "add":
                        if (commands.length() == 5 && commands[3].equals("to")) {
                            Assignment a = new Assignment(commands[1], Integer.parseInt(commands[2]));
                            Course temp = 
                        }
                        else {
                            if (commands[1].equals("course")) {
                                
                            }
                            else if (commands[1].equals("assignment")) {
                            }
                            else {
                                usage();
                            }
                        }
                        break;
                    case "remove":
                        if (commands[1].equals("course")) {
                        }
                        else if (commands[1].equals("assignment")) {
                        }
                        else {
                            usage();
                        }
                        break;
                    case "display":
                        if (commands[1].equals("courses")) {
                            printCourses();
                        }
                        else if (commands[1].equals("assignments")) {
                            printAssignments();
                        }
                        else {
                            usage();
                        }
                        break;
                    case "quit":
                        //storeAssignmentData();
                        System.out.println("Assignment data saved.");
                        break;
                    default:
                        usage();
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                usage();
            }

            System.out.print("User: ");
            input = scanner.nextLine();
            commands = input.split(" ");
        }
*/
