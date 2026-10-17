import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private final List<Student> students = new ArrayList<>();
    private final Path dataFile = Paths.get("students.csv");

    public StudentManager() {
        loadFromFile();
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Student findByRollNumber(String rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber().equalsIgnoreCase(rollNumber.trim())) {
                return student;
            }
        }
        return null;
    }

    public boolean addStudent(Student student) {
        if (findByRollNumber(student.getRollNumber()) != null) {
            return false;
        }
        students.add(student);
        saveToFile();
        return true;
    }

    public boolean updateStudent(String rollNumber, String newName, List<Double> newMarks) {
        Student student = findByRollNumber(rollNumber);
        if (student == null) return false;

        student.setName(newName);
        student.setMarks(newMarks);
        saveToFile();
        return true;
    }

    public boolean deleteStudent(String rollNumber) {
        Student student = findByRollNumber(rollNumber);
        if (student == null) return false;

        students.remove(student);
        saveToFile();
        return true;
    }

    private void loadFromFile() {
        if (!Files.exists(dataFile)) return;

        try (BufferedReader reader = Files.newBufferedReader(dataFile)) {
            String row;
            while ((row = reader.readLine()) != null) {
                Student student = Student.fromCsv(row);
                if (student != null && findByRollNumber(student.getRollNumber()) == null) {
                    students.add(student);
                }
            }
        } catch (IOException ex) {
            System.out.println("Warning: Could not load saved records: " + ex.getMessage());
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = Files.newBufferedWriter(
                dataFile,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE)) {
            for (Student student : students) {
                writer.write(student.toCsv());
                writer.newLine();
            }
        } catch (IOException ex) {
            System.out.println("Warning: Could not save records: " + ex.getMessage());
        }
    }
}
