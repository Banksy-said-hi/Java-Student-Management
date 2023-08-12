package at.aau.studentevidence.domain;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(matriculationNumber, student.matriculationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matriculationNumber);
    }
}

