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

    public void addPerson(Person person) {
        // Checking if a person with the same ID exists
        if (repository.personExistsById(person.getId())) {
            throw new IllegalArgumentException("A person with the same ID already exists in the repository");
        }

        // Checking success of the operation
        if (!repository.addPerson(person)) {
            throw new RuntimeException("Failed to add the person to the repository");
        }
    }

    public List<Student> getAllStudents() {
        return repository.findAllStudents();
    }

    public void removePerson(String id) {
        // Converting the String ID to UUID
        UUID personId = UUID.fromString(id);
        repository.removePersonById(personId);
    }

    public void updatePerson(Person person) {
        if (repository.updatePerson(person)) {
            return;
        }
        throw new RuntimeException("Failed to update the person in the repository");
    }

//    public List<Person> getAllStaff() {
//        return repository.findAllStaff();
//    }

    public Student findStudentById(UUID id) {
        return repository.findStudentById(id);
    }

    public boolean doesStudentExist(Student student) {
        return repository.doesStudentExist(student);
    }
}

