package demo50startwithqueries.app.domain.mappingalltables;

import javax.persistence.Embeddable;

@Embeddable
public class FullName {

    String firstName;
    String lastName;

    public FullName(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public FullName() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString(){
        return firstName + " " + lastName;
    }
}
