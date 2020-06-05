package po83.sulimov.oop;

import java.time.LocalDate;
import java.util.Objects;

public class EntityAccount extends AbstractAccount {
    private String name;

    public EntityAccount(long number, String name) {
        super(number, new EntityTariff(), LocalDate.now());

        Objects.requireNonNull(name, "пустое название");

        this.name = name;

        getTariff().add(new Service("интернет 100мб\\сек", 300, ServiceTypes.INTERNET, LocalDate.now()));
    }

    public EntityAccount(long number, String name, Tariff tariff, LocalDate registrationDate) {
        super(number, tariff, registrationDate);

        Objects.requireNonNull(name, "пустое название");

        this.name = name;
    }

    public void setName(String name) {
        Objects.requireNonNull(name, "пустое название");

        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Entity account:\nentity: %s\n%s", name, super.toString());
    }

    @Override
    public int hashCode() {
        return 53 ^ super.hashCode() ^ name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && name.equals(((EntityAccount)o).getName());
    }
}