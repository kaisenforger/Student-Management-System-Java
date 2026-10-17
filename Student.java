import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String rollNumber;
    private List<Double> marks;

    public Student(String name, String rollNumber, List<Double> marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = new ArrayList<>(marks);
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public List<Double> getMarks() {
        return new ArrayList<>(marks);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(List<Double> marks) {
        this.marks = new ArrayList<>(marks);
    }

    public double getTotal() {
        double total = 0;
        for (double mark : marks) {
            total += mark;
        }
        return total;
    }

    public double getPercentage() {
        return marks.isEmpty() ? 0 : getTotal() / marks.size();
    }

    public String getGrade() {
        double percentage = getPercentage();
        if (percentage >= 90) return "A+";
        if (percentage >= 80) return "A";
        if (percentage >= 70) return "B";
        if (percentage >= 60) return "C";
        if (percentage >= 50) return "D";
        return "F";
    }

    public String toCsv() {
        StringBuilder row = new StringBuilder();
        row.append(rollNumber).append(",").append(name);
        for (double mark : marks) {
            row.append(",").append(mark);
        }
        return row.toString();
    }

    public static Student fromCsv(String row) {
        String[] parts = row.split(",", -1);
        if (parts.length < 3) return null;

        String rollNumber = parts[0];
        String name = parts[1];
        List<Double> marks = new ArrayList<>();

        try {
            for (int i = 2; i < parts.length; i++) {
                marks.add(Double.parseDouble(parts[i]));
            }
            return new Student(name, rollNumber, marks);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format(
            "Roll No: %s | Name: %s | Marks: %s | Total: %.2f | Percentage: %.2f%% | Grade: %s",
            rollNumber, name, marks, getTotal(), getPercentage(), getGrade()
        );
    }
}
