import java.util.PriorityQueue;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.MonthDay;
import java.io.File;

public class AssignmentParser {

    private static File file = new File("assignment_repository.txt");

    public AssignmentParser() {
    }
    
    public void loadWorkspace(Workspace w) {
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

    public void storeAssignmentInfo(Workspace w) {//write
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
}
