import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    ArrayList<Double> grades;

    Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    void addGrade(double grade) {
        grades.add(grade);
    }

    double getAverage() {
        double sum = 0;

        for (double grade : grades) {
            sum += grade;
        }

        return sum / grades.size();
    }

    double getHighest() {
        double highest = grades.get(0);

        for (double grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }

        return highest;
    }

    double getLowest() {
        double lowest = grades.get(0);

        for (double grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }

        return lowest;
    }
}

public class StudentGradeTracker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("\nStudent " + (i + 1));

            System.out.print("Enter student name: ");
            String name = sc.nextLine();

            Student student = new Student(name);

            System.out.print("Enter number of subjects: ");
            int subjects = sc.nextInt();

            for (int j = 0; j < subjects; j++) {
                System.out.print("Enter grade for subject " + (j + 1) + ": ");
                double grade = sc.nextDouble();
                student.addGrade(grade);
            }

            sc.nextLine();
            students.add(student);
        }

        System.out.println("\n========== STUDENT GRADE REPORT ==========");

        for (Student student : students) {
            System.out.println("\nStudent Name : " + student.name);
            System.out.printf("Average      : %.2f%n", student.getAverage());
            System.out.printf("Highest      : %.2f%n", student.getHighest());
            System.out.printf("Lowest       : %.2f%n", student.getLowest());
        }

        System.out.println("\n==========================================");

        sc.close();
    }
}