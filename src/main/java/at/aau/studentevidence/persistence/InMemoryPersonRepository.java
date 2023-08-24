package at.aau.studentevidence.persistence;

import at.aau.studentevidence.domain.Person;
import at.aau.studentevidence.domain.Staff;
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

    public List<Person> findAll() { return new ArrayList<>(storage);}

    // INPUT SANITIZATION REQUIRED
    public Boolean addPerson(Person person) {
        return storage.add(person);
    }

    public void removePersonById(UUID id) {
        storage.removeIf(person -> id.equals(person.getId()));
    }

    public boolean updatePerson(Person personToUpdate) {
        Person personToRemove = null;

        // Find the person in the storage based on the id
        for (Person existingPerson : storage) {
            if (personToUpdate.getId().equals(existingPerson.getId())) {
                personToRemove = existingPerson;
                break;  // Break out of the loop once the person is found
            }
        }

        if (personToRemove != null) {
            // Remove the old person
            storage.remove(personToRemove);
            // Add the updated person
            storage.add(personToUpdate);

            return true;  // Successfully updated
        }

        System.out.println("Inspecting the storage again");
        System.out.println("Current storage: " + storage);

        return false; // No person found with the given id
    }

     // Finding all the students stored on our memory
    public List<Student> findAllStudents() {
         return storage.stream()
                       .filter(person -> person instanceof Student)
                       .map(person -> (Student) person)
                       .collect(Collectors.toList());
    }


     // Finding all the staff stored on our memory
    public List<Staff> findAllStaff() {
        return storage.stream()                      // 1
                .filter(person -> person instanceof Staff) // 2
                .map(person -> (Staff) person) // 3
                .collect(Collectors.toList()); // 4
    }


    public Student findStudentById(UUID id) {
        return storage.stream()
                .filter(person -> person instanceof Student && id.equals(person.getId()))
                .map(person -> (Student) person) // Cast the found Person object to Student
                .findFirst()
                .orElse(null);
    }

    public Staff findStaffById(UUID id) {
        return storage.stream()
                .filter(person -> person instanceof Staff && id.equals(person.getId()))
                .map(person -> (Staff) person)
                .findFirst()
                .orElse(null);
    }

    // Making sure a student with the same credentials does not exist
     public boolean doesStudentExist(Student student) {
        return storage.stream()
                .anyMatch(s -> s.getName().equals(student.getName())
                        && s.getEmailAddress().equals(student.getEmailAddress())
                        && s.getPhoneNumber().equals(student.getPhoneNumber()));
    }

    // Making sure a staff with the same credentials does not exist
    public boolean doesStaffExist(Staff staff) {
        return storage.stream()
                .anyMatch(s -> s.getName().equals(staff.getName())
                        && s.getEmailAddress().equals(staff.getEmailAddress())
                        && s.getPhoneNumber().equals(staff.getPhoneNumber()));
    }

    // Making sure a student with the same Person ID does not already exist
    public boolean personExistsById(UUID id) {
        return storage.stream().anyMatch(person -> id.equals(person.getId()));
    }
}


