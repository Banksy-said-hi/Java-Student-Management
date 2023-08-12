package at.aau.studentevidence.domain;

import java.util.UUID;

public class Student extends Person {
    private String matriculationNumber;

    // Constructors
    public Student() {
        this.matriculationNumber = UUID.randomUUID().toString();
    }

    public Student(String name, String phoneNumber, String emailAddress) {
        super(name, phoneNumber, emailAddress); // Calling the constructor of the superclass (Person)
    }

    // Getter and setter for matriculationNumber
    public String getMatriculationNumber() {
        return matriculationNumber;
    }

    public void setMatriculationNumber(String matriculationNumber) {
        this.matriculationNumber = matriculationNumber;
    }
}

