package at.aau.studentevidence.domain;

import java.util.Objects;
import java.util.Random;

public class Student extends Person {
    private static final Random random = new Random();
    String matriculationNumber;

    // Constructors
    public Student() {
        super(); // Calling the constructor of the superclass (Person);
        this.matriculationNumber = generateMatriculationNumber();
    }

    // add a validation check while generating a unique matriculation number
    // for each added student before assigning the MN to the student
    // to make sure no two students have a common MN
    private String generateMatriculationNumber() {
        long randomLong = 1000000000L + random.nextInt(900000000);
        matriculationNumber = String.valueOf(randomLong);

        return matriculationNumber;
    }

    // Getter for matriculationNumber
    public String getMatriculationNumber() {
        return matriculationNumber;
    }



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

