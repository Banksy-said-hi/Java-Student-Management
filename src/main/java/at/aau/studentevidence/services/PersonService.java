package at.aau.studentevidence.services;

import at.aau.studentevidence.domain.Person;
import at.aau.studentevidence.domain.Student;
import at.aau.studentevidence.persistence.InMemoryPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Markus Schneider
 * @since 23.05.2022
 */
@Service
public class PersonService {

    private final InMemoryPersonRepository repository;

    @Autowired
    public PersonService(InMemoryPersonRepository repository) {
        this.repository = repository;
    }

//    public Person findById(String id) {
//        // Converting the String ID to UUID
//        UUID personId = UUID.fromString(id);
//        return repository.findPersonById(personId);
//    }

    public List<Student> getAllStudents() {
        return repository.findAllStudents();
    }

//    public List<Person> getAllStaff() {
//        // Assuming there's a method to get all staff members in your repository.
//        // If not, you'll need to implement this.
//        return repository.findAllStaff();
//    }

    public void addPerson(Person person) {
        // Here I'm assuming `addPerson` in your repository returns a boolean indicating the success.
        // If it throws an exception or behaves differently, you'll need to adjust this.
        if (!repository.addPerson(person)) {
            throw new RuntimeException("Failed to add the person to the repository");
        }
    }

//    public void updatePerson(Person person) {
//        // Assuming there's a method to update a person in your repository.
//        // If not, you'll need to implement this.
//        if (!repository.updatePerson(person)) {
//            throw new RuntimeException("Failed to update the person in the repository");
//        }
//    }

    public void removePerson(String id) {
        // Converting the String ID to UUID
        UUID personId = UUID.fromString(id);
        repository.removePersonById(personId);
    }

    public boolean doesStudentExist(Student student) {
        return repository.doesStudentExist(student);
    }
}

