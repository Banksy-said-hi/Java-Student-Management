package at.aau.studentevidence.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Student extends Person {
    private String matriculationNumber;
    private static int counter = 1;

    // Constructors
    public Student() {
        super(); // Calling the constructor of the superclass (Person)
        // The default constructor will have a default matriculation number for demonstration
        this.matriculationNumber = "STU" + counter;
        counter++;
    }

    // (******) Find out why this contructor is never used
//    public Student(String name, String phoneNumber, String emailAddress) {
//        super(name, phoneNumber, emailAddress); // Calling the constructor of the superclass (Person)
////        this.matriculationNumber = generateMatriculationNumber(name, phoneNumber);
//        this.matriculationNumber = "STU" + counter;
//        counter++;
//    }

    // add a validation check while generating a unique matriculation number
    // for each added student before assigning the MN to the student
    // to make sure no two students have a common MN
    private String generateMatriculationNumber(String name, String phoneNumber) {
        // Extracting the first 4 letters of the name
        String firstFourLetters = name.length() >= 4 ? name.substring(0, 4).toUpperCase() : name.toUpperCase();

        // Extracting the last 5 digits of the phone number
        String lastFiveDigits = phoneNumber.length() >= 5 ? phoneNumber.substring(phoneNumber.length() - 5) : phoneNumber;

        // Getting the current year
        int currentYear = LocalDate.now().getYear();

        return firstFourLetters + lastFiveDigits + currentYear;
    }

    // Getter and setter for matriculationNumber
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

