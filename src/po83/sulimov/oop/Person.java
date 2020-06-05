package po83.sulimov.oop;

import java.util.Objects;

public class Person {
    private String firstName;
    private String secondName;

    public Person() {
        this.firstName = "";
        this.secondName = "";
    }

    public Person(String firstName, String secondName) {
        Objects.requireNonNull(firstName, "передано пустое имя");
        Objects.requireNonNull(secondName, "передано пустая фамилия");

        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    @Override
    public String toString() {
        return String.format("%s %s", firstName, secondName);
    }

    @Override
    public int hashCode() {
        return firstName.hashCode() * secondName.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this.getClass() == o.getClass()) {
            return firstName.equals(((Person) o).getFirstName()) && secondName.equals(((Person) o).getSecondName());
        } else {
            return false;
        }
    }
}

