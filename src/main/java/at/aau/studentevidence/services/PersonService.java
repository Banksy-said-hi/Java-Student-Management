package at.aau.studentevidence.services;

import at.aau.studentevidence.domain.Person;
import at.aau.studentevidence.persistence.InMemoryPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Person findById(String id) {
        //todo implement
        return null;
    }

    public List<Person> getAllStudents() {
        //todo implement
        return null;
    }

    public List<Person> getAllStaff() {
        //todo implement
        return null;
    }

    public void addPerson(Person person) {
        //todo implement
    }

    public void updatePerson(Person person) {
        //todo implement
    }

    public void removePerson(String id) {
        //todo implement
    }
}
