package po83.sulimov.oop;

import java.time.LocalDate;
import java.util.Objects;

public abstract class AbstractAccount implements Account {
    private long number;
    private Tariff tariff;
    private LocalDate registrationDate;

    protected AbstractAccount(long number, Tariff tariff, LocalDate registrationDate) {
        Objects.requireNonNull(tariff, "тариф пустой");
        Objects.requireNonNull(registrationDate, "дата регистрации пустая");

        if (!checkAccountNumber(number)) {
            throw new IllegalAccountNumber("номер не соответствует формату");
        }

        if (registrationDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("передана дата регистрации из будущего");
        }

        this.number = number;
        this.tariff = tariff;
        this.registrationDate = registrationDate;
    }

    @Override
    public long getNumber() {
        return number;
    }

    @Override
    public Tariff getTariff() {
        return tariff;
    }

    @Override
    public void setTariff(Tariff tariff) {
        Objects.requireNonNull(tariff, "передан пустой тариф");

        this.tariff = tariff;
    }

    @Override
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public String toString() {
        return String.format("number: %d\n%s\n%s", number, tariff.toString(), registrationDate.toString());
    }

    @Override
    public int hashCode() {
        return Long.hashCode(number) * tariff.size() * registrationDate.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this.getClass() == o.getClass()) {
            AbstractAccount account = (AbstractAccount) o;
            return (number == account.number &&
                    tariff.size() == account.tariff.size() && tariff.equals(account.getTariff()) &&
                    registrationDate.equals(((AbstractAccount)o).getRegistrationDate()));
        } else {
            return false;
        }
    }

    public static boolean checkAccountNumber(long accountNumber){
        return accountNumber >= 1000000000001L && accountNumber <= 999999999999999L;
    }
}