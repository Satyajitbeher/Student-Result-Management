package manager;

import model.Student;
import java.io.*;
import java.util.*;

public class ResultManager {
    private static final String FILE = "students.txt";

    public void addStudent(Student s) {
        try (FileWriter fw = new FileWriter(FILE, true)) {
            fw.write(s.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Student.fromString(line));
            }
        } catch (IOException e) {
            // File not found is OK
        }
        return list;
    }

    public Student search(String roll) {
        for (Student s : getAllStudents()) {
            if (s.getRoll().equals(roll)) return s;
        }
        return null;
    }
}
