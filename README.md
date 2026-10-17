# Student Management System (Java)

A beginner-friendly, menu-driven Java console application for managing student records. It demonstrates object-oriented programming, Java collections, input validation, and file handling.

## Features

- Add a student with a unique roll number
- View all student records
- Search by roll number
- Update student name and marks
- Delete a student record
- Calculate total marks, percentage, and grade
- Save records to `students.csv` so data remains available after restarting the program
- Validate menu choices and marks (0–100)

## Requirements

- Java JDK 8 or later
- A terminal / command prompt, or an IDE such as IntelliJ IDEA, Eclipse, or VS Code

## Run from the terminal

1. Download or clone this repository.
2. Open a terminal in the project folder.
3. Compile the source files:

   ```bash
   javac -d out src/Student.java src/StudentManager.java src/Main.java
   ```

4. Run the application:

   ```bash
   java -cp out Main
   ```

The program creates `students.csv` in the folder from which you run it when records are saved. Keep that file if you want to keep your sample data.

## Project structure

```text
StudentManagementSystem/
├── src/
│   ├── Main.java
│   ├── Student.java
│   └── StudentManager.java
├── README.md
└── .gitignore
```

## Concepts used

- Classes and objects
- Encapsulation
- ArrayList and List
- Loops, methods, and conditional statements
- Exception handling
- File reading and writing
- CSV-based data persistence

## Grade scale

| Percentage | Grade |
|---|---|
| 90–100 | A+ |
| 80–89.99 | A |
| 70–79.99 | B |
| 60–69.99 | C |
| 50–59.99 | D |
| Below 50 | F |

This grade scale is a simple example for this project and may differ from your institution's grading policy.

## Notes

- Roll numbers must be unique.
- Enter student names without commas because this starter project uses a simple CSV format.
- This is an educational project, not a production student-information system.
