package studentdemo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentExamResultAnalytics {
    public static void main(String[] args) {
        List<Student> students = buildStudents();

        Set<String> uniqueCourses = students.stream()
                .map(Student::getCourse)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        Map<String, List<Student>> studentsByCourse = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse));

        List<Student> passedStudents = students.stream()
                .filter(Student::isPassed)
                .collect(Collectors.toList());

        int totalMarks = passedStudents.stream()
                .mapToInt(Student::getMarks)
                .sum();

        double averageMarks = passedStudents.stream()
                .mapToInt(Student::getMarks)
                .average()
                .orElse(0.0);

        Optional<Student> highestScorer = passedStudents.stream()
                .max(Comparator.comparingInt(Student::getMarks));

        List<Student> sortedPassedStudents = passedStudents.stream()
                .sorted(Comparator.comparingInt(Student::getMarks)
                        .reversed()
                        .thenComparing(Student::getStudentName))
                .collect(Collectors.toList());

        Map<String, Integer> courseWiseTotalMarks = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getCourse,
                        Collectors.summingInt(Student::getMarks)));

        Optional<Student> student110 = students.stream()
                .filter(student -> student.getStudentId() == 110)
                .findFirst();

        StudentFilter marksAtLeast75 = student -> student.getMarks() >= 75;

        System.out.println("Unique Courses (Set)");
        System.out.println(uniqueCourses);

        System.out.println("Students Grouped by Course (Map)");
        studentsByCourse.forEach((course, courseStudents) -> {
            List<String> names = courseStudents.stream()
                    .map(Student::getStudentName)
                    .collect(Collectors.toList());
            System.out.println(course + "  " + names);
        });

        System.out.println("Passed Students (Filtered)");
        System.out.println(passedStudents.stream()
                .map(Student::getStudentName)
                .collect(Collectors.joining(", ")));

        System.out.println("Total Marks of Passed Students");
        System.out.println(totalMarks);

        System.out.println("Average Marks of Passed Students");
        System.out.printf("%.2f%n", averageMarks);

        System.out.println("Highest Marks Scorer");
        highestScorer.ifPresent(student ->
                System.out.println(student.getStudentName() + "  " + student.getMarks()));

        System.out.println("Sorted Passed Students (Marks, Name)");
        sortedPassedStudents.forEach(student ->
                System.out.println(student.getStudentName() + "  " + student.getMarks()));

        System.out.println("Course-wise Total Marks (Map)");
        courseWiseTotalMarks.forEach((course, total) ->
                System.out.println(course + "  " + total));

        System.out.println("Optional Search Result (studentId = 110)");
        if (student110.isPresent()) {
            Student student = student110.get();
            System.out.println("Student found: " + student.getStudentName()
                    + "  " + student.getCourse() + "  " + student.getMarks());
        } else {
            System.out.println("Student not found");
        }

        System.out.println("Students with Marks >= 75 (Functional Interface Filter)");
        students.stream()
                .filter(marksAtLeast75::check)
                .forEach(student ->
                        System.out.println(student.getStudentName() + "  " + student.getMarks()));

        System.out.println("Final Report Printed Using Method Reference");
        sortedPassedStudents.forEach(System.out::println);
    }

    private static List<Student> buildStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(101, "Anu", "Java", 82, true));
        students.add(new Student(102, "Bala", "Java", 45, false));
        students.add(new Student(103, "Charan", "Python", 91, true));
        students.add(new Student(104, "Divya", "Java", 67, true));
        students.add(new Student(105, "Esha", "Python", 38, false));
        students.add(new Student(106, "Farhan", "DevOps", 74, true));
        students.add(new Student(107, "Gokul", "DevOps", 88, true));
        students.add(new Student(108, "Hari", "Java", 53, true));
        students.add(new Student(109, "Isha", "Python", 79, true));
        students.add(new Student(110, "John", "DevOps", 62, true));
        students.add(new Student(111, "Kavya", "Java", 95, true));
        students.add(new Student(112, "Lokesh", "Python", 49, false));
        return students;
    }
}
