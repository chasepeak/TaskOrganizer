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
                String[] date = words[7].split("/");
                Course c = new Course(words[0], words[1]);
                Assignment a = new Assignment(words[3],
                    MonthDay.of(Integer.parseInt(date[0]), Integer.parseInt(date[1])));
                    int i;
                    if ((i = w.getCourses().indexOf(c)) != -1) {
                        Course currentCourse = w.getCourses().get(i);
                        currentCourse.getAssignments().add(a);
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
        //stores the assignments like "MATH 452 : homework1 is due on 4/10"
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Course c: w.getCourses()) {
                String[] line = new String[2];
                line[0] = c.toString() + " :";
                String[] temp = line;
                for (Assignment a: c.getAssignments()) {
                    line[1] = a.toString();
                    for (String s:line) {//just print the whole string array at once?
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
