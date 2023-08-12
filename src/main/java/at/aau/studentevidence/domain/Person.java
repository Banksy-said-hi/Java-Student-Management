package at.aau.studentevidence.domain;

/**
 * @author Markus Schneider
 * @since 23.05.2022
 */
//todo derive class Student and class Staff and implement specific properties and methods

//todo implement properties and their getters/setters

//todo implement methods equals, hashCode and toString
public abstract class Person {

//    private Long id;  // Assuming you're using Long for the id.
    private String name;
    private String phoneNumber;
    private String emailAddress;

    // Constructors
    public Person() {
    }

    public Person(String name, String phoneNumber, String emailAddress) {
//        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    // Getters and setters for these properties
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
