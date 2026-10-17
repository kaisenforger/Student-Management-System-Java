import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManager manager = new StudentManager();

    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("   STUDENT MANAGEMENT SYSTEM");
        System.out.println("=================================");

        boolean running = true;
        while (running) {
            showMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewAllStudents(); break;
                case 3: searchStudent(); break;
                case 4: updateStudent(); break;
                case 5: deleteStudent(); break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using the system!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1-6.");
            }
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\\n----------- MENU -----------");
        System.out.println("1. Add student");
        System.out.println("2. View all students");
        System.out.println("3. Search student");
        System.out.println("4. Update student");
        System.out.println("5. Delete student");
        System.out.println("6. Exit");
        System.out.println("----------------------------");
    }

    private static void addStudent() {
        System.out.print("Enter roll number: ");
        String roll = scanner.nextLine().trim();
        if (roll.isEmpty()) {
            System.out.println("Roll number cannot be empty.");
            return;
        }

        System.out.print("Enter student name (do not use commas): ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty() || name.contains(",")) {
            System.out.println("Please enter a valid name without commas.");
            return;
        }

        List<Double> marks = readMarks();
        Student student = new Student(name, roll, marks);

        if (manager.addStudent(student)) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("That roll number already exists.");
        }
    }

    private static void viewAllStudents() {
        List<Student> students = manager.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\\n--- STUDENT RECORDS ---");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void searchStudent() {
        System.out.print("Enter roll number to search: ");
        String roll = scanner.nextLine().trim();
        Student student = manager.findByRollNumber(roll);

        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println(student);
        }
    }

    private static void updateStudent() {
        System.out.print("Enter roll number to update: ");
        String roll = scanner.nextLine().trim();

        Student existing = manager.findByRollNumber(roll);
        if (existing == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Current record: " + existing);
        System.out.print("Enter new name (do not use commas): ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty() || name.contains(",")) {
            System.out.println("Please enter a valid name without commas.");
            return;
        }

        List<Double> marks = readMarks();
        if (manager.updateStudent(roll, name, marks)) {
            System.out.println("Student updated successfully.");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter roll number to delete: ");
        String roll = scanner.nextLine().trim();

        if (manager.deleteStudent(roll)) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static List<Double> readMarks() {
        int subjectCount;
        do {
            subjectCount = readInt("How many subjects (1-10)? ");
            if (subjectCount < 1 || subjectCount > 10) {
                System.out.println("Enter a number from 1 to 10.");
            }
        } while (subjectCount < 1 || subjectCount > 10);

        List<Double> marks = new ArrayList<>();
        for (int i = 0; i < subjectCount; i++) {
            double mark;
            do {
                mark = readDouble("Enter marks for subject " + (i + 1) + " (0-100): ");
                if (mark < 0 || mark > 100) {
                    System.out.println("Marks must be between 0 and 100.");
                }
            } while (mark < 0 || mark > 100);
            marks.add(mark);
        }
        return marks;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
