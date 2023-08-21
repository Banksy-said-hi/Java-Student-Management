package at.aau.studentevidence.services;

import at.aau.studentevidence.domain.Course;
import at.aau.studentevidence.persistence.InMemoryCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseService {

    private final InMemoryCourseRepository courseRepository;
    @Autowired
    public CourseService(InMemoryCourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Service method to save a course
    public Course saveCourse(Course course) {
        // Check if a course with the same credentials exists
        Optional<Course> existingCourse = courseRepository.findAll().stream()
                .filter(c -> c.getTitle().equals(course.getTitle()) &&
                        c.getSemester() == course.getSemester() &&
                        c.getState() == course.getState())
                .findFirst();

        if (existingCourse.isPresent()) {
            throw new IllegalArgumentException("A course with the same credentials already exists!");
        }

        return courseRepository.save(course);
    }


    // Service method to retrieve a course by its ID
//    public Optional<Course> findCourseById(Long id) {
//        return courseRepository.findById(id);
//    }

    //Service method to retrieve all courses
    public Set<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    // Service method to delete a course
//    public void deleteCourse(Long id) {
//        courseRepository.deleteById(id);
//    }

    // Add more methods here as needed.
}
