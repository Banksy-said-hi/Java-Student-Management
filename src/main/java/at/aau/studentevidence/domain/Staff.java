package at.aau.studentevidence.domain;

import java.util.Objects;
import java.util.Random;

public class Staff extends Person {
    private static final Random random = new Random();
    String socialSecurity;

    // Constructors
    public Staff() {
        super(); // Calling the constructor of the superclass (Person);
        this.socialSecurity = generateSocialSecurity();
    }

    private String generateSocialSecurity() {
        long randomLong = 1000000000L + random.nextInt(900000000);
        socialSecurity = String.valueOf(randomLong);

        return socialSecurity;
    }

    // Getter and Setter for matriculationNumber
    public String getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(String socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return Objects.equals(socialSecurity, staff.socialSecurity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socialSecurity);
    }
}

