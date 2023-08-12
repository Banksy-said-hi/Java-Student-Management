package at.aau.studentevidence.persistence;

import at.aau.studentevidence.domain.Course;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Markus Schneider
 * @since 23.05.2022
 */
@Component
public class InMemoryCourseRepository {
    private final Set<Course> storage = new HashSet<>();


}
