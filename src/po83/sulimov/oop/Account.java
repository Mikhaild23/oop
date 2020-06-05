package po83.sulimov.oop;

import java.time.LocalDate;

public interface Account {
    long getNumber();

    Tariff getTariff();

    void setTariff(Tariff tariff);

    LocalDate getRegistrationDate();
}
