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
//10f529b9-475f-4845-bcd1-7ecffa45caa9

@Component
public class InMemoryPersonRepository {
    private final Set<Person> storage = new HashSet<>();

    public List<Person> findAll() { return new ArrayList<>(storage);}

    // ???
    // Warning
    // no input sanitization has been made
    public Boolean addPerson(Person person) {
        return storage.add(person);
    }

    public void removePersonById(UUID id) {
        storage.removeIf(person -> id.equals(person.getId()));
    }


    public boolean updatePerson(Person personToUpdate) {
        System.out.println("Person reached the memory successfully");
        System.out.println("ID at the Memory: " + personToUpdate.getId());
        System.out.println("=================");
        System.out.println("Inspecting the storage");
        System.out.println("Current storage: " + storage);

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

            System.out.println("Inspecting the storage after update");
            System.out.println("Current storage: " + storage);
            return true;  // Successfully updated
        }

        System.out.println("Inspecting the storage again");
        System.out.println("Current storage: " + storage);

        return false; // No person found with the given id
    }

    // You may need to add a method to find a person by their ID if "id" is a field in your Person class.
    public Student findStudentById(UUID id) {
        return storage.stream()
                .filter(person -> person instanceof Student && id.equals(person.getId()))
                .map(person -> (Student) person) // Cast the found Person object to Student
                .findFirst()
                .orElse(null);
    }

     public List<Student> findAllStudents() {
         return storage.stream()
                       .filter(person -> person instanceof Student)
                       .map(person -> (Student) person)
                       .collect(Collectors.toList());
     }

     // Making sure a student with the same credentials does not exist
     public boolean doesStudentExist(Student student) {
        return storage.stream()
                .anyMatch(s -> s.getName().equals(student.getName())
                        && s.getEmailAddress().equals(student.getEmailAddress())
                        && s.getPhoneNumber().equals(student.getPhoneNumber()));
    }

    // Making sure a student with the same Person ID does not already exist
    public boolean personExistsById(UUID id) {
        return storage.stream().anyMatch(person -> id.equals(person.getId()));
    }

//    public boolean doesMatriculationNumberExist(String matriculationNumber) {
//        return storage.stream()
//                .filter(person -> person instanceof Student) // Filter only instances of Student
//                .anyMatch(student -> matriculationNumber.equals(((Student) student).getMatriculationNumber()));
//    }
}


