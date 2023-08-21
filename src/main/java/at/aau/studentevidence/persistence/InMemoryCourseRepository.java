package at.aau.studentevidence.persistence;

import at.aau.studentevidence.domain.Course;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Markus Schneider
 * @since 23.05.2022
 */
@Component
public class InMemoryCourseRepository {

    private final Set<Course> storage = new HashSet<>();

    // Add or update a course
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
    public Optional<Course> findById(Long id) {
        return storage.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst();
    }

    // Delete a course
    public void delete(Course course) {
        storage.remove(course);
    }
}

