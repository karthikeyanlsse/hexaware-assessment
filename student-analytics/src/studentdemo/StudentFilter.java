package studentdemo;

@FunctionalInterface
public interface StudentFilter {
    boolean check(Student student);
}
