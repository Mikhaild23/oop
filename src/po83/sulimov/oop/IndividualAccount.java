package po83.sulimov.oop;

import java.time.LocalDate;
import java.util.Objects;

public class IndividualAccount extends AbstractAccount {
    private Person person;

    public IndividualAccount(long number, Person person) {
        super(number, new IndividualsTariff(), LocalDate.now());

        Objects.requireNonNull(person, "передан пустой клиент");

        this.person = person;

        getTariff().add(new Service("интернет 100мб\\сек", 300, ServiceTypes.INTERNET, LocalDate.now()));
    }

    public IndividualAccount(long number, Person person, Tariff tariff, LocalDate registrationDate) {
        super(number, tariff, registrationDate);

        Objects.requireNonNull(person, "передан пустой клиент");

        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        Objects.requireNonNull(person, "передан пустой клиент");

        this.person = person;
    }

    @Override
    public String toString() {
        return String.format("Entity account:\nholder: %s\n%s", person.toString(), super.toString());
    }

    @Override
    public int hashCode() {
        return 97 ^ super.hashCode() ^ person.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && person.equals(((IndividualAccount)o).getPerson());
    }
}
