package at.aau.studentevidence.domain;

import java.util.*;

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

    // Enums to restrict the values of Semester and State.
    public enum Semester {
        WINTER, SUMMER
    }
    public enum State {
        ACTIVE, INACTIVE
    }

    public Staff getTeacher() {
        return teacher;
    }
    public void setTeacher(Staff teacher) {
        this.teacher = teacher;
    }
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


    // Constructor initializing the fields and generating a unique ID.
    public Course(String title, Semester semester) {
        this.id = generateUniqueID(title);
        this.title = title;
        this.semester = semester;
        this.state = State.ACTIVE;
        this.students = new HashSet<>();
    }

    private String generateUniqueID(String title) {
        String prefix = title.length() >= 4 ? title.substring(0, 4).toUpperCase() : title.toUpperCase();
        String uniqueNumber = String.format("%06d", new Random().nextInt(999999));
        return prefix + "-" + uniqueNumber;
    }

    // Enrolls a student to the course.
    public void addStudent(Student student) {
        this.students.add(student);
    }
    public Set<Student> getStudents() {
        return new HashSet<>(students);
    }
    // Assigns a staff member to the course.
    public void assignTeacher(Staff teacher) {
        this.teacher = teacher;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", semester=" + semester +
                ", state=" + state +
                ", students=" + students.size() +  // Just showing the count of students
                ", teacher=" + (teacher != null ? teacher.getName() : "No teacher assigned") +  // assuming Staff has a getName method
                '}';
    }

}
