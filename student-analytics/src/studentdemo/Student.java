package studentdemo;

public class Student {
    private final int studentId;
    private final String studentName;
    private final String course;
    private final int marks;
    private final boolean passed;

    public Student(int studentId, String studentName, String course, int marks, boolean passed) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.course = course;
        this.marks = marks;
        this.passed = passed;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourse() {
        return course;
    }

    public int getMarks() {
        return marks;
    }

    public boolean isPassed() {
        return passed;
    }

    @Override
    public String toString() {
        return studentName + "  " + course + "  " + marks;
    }
}
