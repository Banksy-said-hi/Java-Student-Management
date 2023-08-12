package at.aau.studentevidence.domain;

import java.util.Objects;
import java.util.UUID;

public class Student extends Person {
    private int matriculationNumber;
    private static int counter = 0;

    // Constructors
    public Student() {
        this.matriculationNumber = ++counter;
    }

    public Student(String name, String phoneNumber, String emailAddress) {
        super(name, phoneNumber, emailAddress); // Calling the constructor of the superclass (Person)
    }

    // Getter and setter for matriculationNumber
    public int getMatriculationNumber() {
        return matriculationNumber;
    }

//    public void setMatriculationNumber(String matriculationNumber) {
//        this.matriculationNumber = matriculationNumber;
//    }

    // This function needs to be studied more to find more about
    // the goal it serves as well as its component
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

