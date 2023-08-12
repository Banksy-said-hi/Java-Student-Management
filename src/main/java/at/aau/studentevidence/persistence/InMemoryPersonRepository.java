package at.aau.studentevidence.persistence;

import at.aau.studentevidence.domain.Person;
import at.aau.studentevidence.domain.Student;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Markus Schneider
 * @since 23.05.2022
 */

@Component
public class InMemoryPersonRepository {
    private final Set<Student> storage = new HashSet<>();

    public List<Student> findAll() {
        return new ArrayList<>(storage);
    }

    public Boolean addPerson(Student student) {
        return storage.add(student);
    }
}
