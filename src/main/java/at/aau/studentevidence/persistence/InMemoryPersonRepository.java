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
    private final Set<Person> storage = new HashSet<>();

//    public List<Person> findAll() {
//        return new ArrayList<>(storage);
//    }

    // ???
    // Warning
    // no input sanitization has been made
    public Boolean addPerson(Person person) {
        return storage.add(person);
    }

    public void removePersonById(UUID id) {
        storage.removeIf(person -> id.equals(person.getId()));
    }

    // You may need to add a method to find a person by their ID if "id" is a field in your Person class.
    public Person findPersonById(UUID id) {
        return storage.stream()
                .filter(person -> id.equals(person.getId()))
                .findFirst()
                .orElse(null);
    }

     public List<Student> findAllStudents() {
         return storage.stream()
                       .filter(person -> person instanceof Student)
                       .map(person -> (Student) person)
                       .collect(Collectors.toList());
     }

    public boolean doesStudentExist(Student student) {
        return storage.stream()
                .anyMatch(s -> s.getName().equals(student.getName())
                        && s.getEmailAddress().equals(student.getEmailAddress())
                        && s.getPhoneNumber().equals(student.getPhoneNumber()));
    }
}


