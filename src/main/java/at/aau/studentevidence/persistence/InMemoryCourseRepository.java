package at.aau.studentevidence.persistence;

import at.aau.studentevidence.domain.Course;
import at.aau.studentevidence.domain.Student;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

/**
 * @author Markus Schneider
 * @since 23.05.2022
 */
@Component
public class InMemoryCourseRepository {

    private final Set<Course> storage = new HashSet<>();

//    public InMemoryCourseRepository() {
//        storage  = new HashSet<>();
//        initData();
//    }

//    private void initData() {
//        Course course1 = new Course("MATHEMATICS", Course.Semester.WINTER);
//        Course course2 = new Course("PHYSICS", Course.Semester.SUMMER);
//
//        storage.add(course1);
//        storage.add(course2);
//    }

    public Course save(Course course) {
        storage.add(course);
        // Return the saved course
        return course;
    }

    // Retrieve all courses
    public Set<Course> findAll() {
        return new HashSet<>(storage);
    }

    // Find a course by its ID
    public Course findCourseById(String id) {
        return storage.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Course with ID " + id + " not found"));
    }


    // Delete a course
    public void delete(Course course) {
        storage.remove(course);
    }
}

