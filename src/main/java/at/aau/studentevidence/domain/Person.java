package at.aau.studentevidence.domain;

import java.util.UUID;

/**
 * @author Markus Schneider
 * @since 23.05.2022
 */
//todo derive class Student and class Staff and implement specific properties and methods

//todo implement properties and their getters/setters

//todo implement methods equals, hashCode and toString
public abstract class Person {

    private UUID id;
    private String name;
    private String phoneNumber;
    private String emailAddress;

    // Constructors
    public Person() {
        this.id = UUID.randomUUID();
    }

    public Person(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.id = UUID.randomUUID();
    }

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

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // If they reference the same object
        if (o == null || getClass() != o.getClass()) return false; // Null check and class check

        Person person = (Person) o;

        return id != null ? id.equals(person.id) : person.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
