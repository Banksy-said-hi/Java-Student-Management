package at.aau.studentevidence.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.Random;

/**
 * Represents a Course within the system.
 *
 * @author Markus Schneider
 * @since 23.05.2022
 */
public class Course {

    // Unique identifier for each course.
    private String id = "";

    // Represents the title of the course.
    private String title;

    // Represents the semester for the course (either WINTER or SUMMER).
    private Semester semester;

    // Represents the current state of the course (either ACTIVE or INACTIVE).
    private State state;

    // List of students enrolled in this course.
    private Set<Student> students;

    // The staff member teaching this course.
    private Staff teacher;
    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    // Enums to restrict the values of Semester and State.
    public enum Semester {
        WINTER, SUMMER
    }

    public enum State {
        ACTIVE, INACTIVE
    }

    // Constructor initializing the fields and generating a unique ID.
    public Course(String title, Semester semester) {
        this.id = generateUniqueID(title);
        this.title = title;
        this.semester = semester;
        this.state = State.ACTIVE;  // Courses are ACTIVE by default.
        this.students = new HashSet<>();
    }

    private String generateUniqueID(String title) {
        String prefix = title.length() >= 4 ? title.substring(0, 4).toUpperCase() : title.toUpperCase();
        String uniqueNumber = String.format("%06d", new Random().nextInt(999999));
        return prefix + "-" + uniqueNumber;
    }

    // Enrolls a student to the course.
    public void enrollStudent(Student student) {
        this.students.add(student);
    }

    // Assigns a staff member to the course.
    public void assignTeacher(Staff teacher) {
        this.teacher = teacher;
    }
}
