package po83.sulimov.oop;

import java.time.LocalDate;
import java.util.Objects;

public final class Service implements Cloneable, Comparable<Service> {
    final private String DEFAULT_NAME = "интернет 100мб\\сек";
    final private double DEFAULT_COST = 300;
    final private String name;
    final private double cost;
    final private ServiceTypes serviceType;
    final private LocalDate activationDate;


    public Service() {
        name = DEFAULT_NAME;
        cost = DEFAULT_COST;
        serviceType = ServiceTypes.INTERNET;
        activationDate = LocalDate.now();
    }

    public Service(String name, double cost, ServiceTypes serviceType, LocalDate activationDate) {
        Objects.requireNonNull(name, "название пустое");
        Objects.requireNonNull(serviceType, "тип сервиса пустой");
        Objects.requireNonNull(activationDate, "дата активации пустая");

        if (cost < 0) {
            throw new IllegalArgumentException("передана стоимость меньше нуля");
        }

        if (activationDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("передана дата активации из будущего");
        }

        this.name = name;
        this.cost = cost;
        this.serviceType = serviceType;
        this.activationDate = activationDate;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public ServiceTypes getServiceType() {
        return serviceType;
    }

    public LocalDate getActivationDate() {
        return activationDate;
    }

    @Override
    public String toString() {
        return String.format("%.40s\\%fр. %s", name, cost, activationDate);
    }

    @Override
    public int hashCode() {
        return name.hashCode() * Double.hashCode(cost) * activationDate.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this.getClass() == o.getClass()) {
            return name.equals(((Service) o).getName()) &&
                    cost == ((Service) o).getCost() && serviceType == ((Service) o).getServiceType() &&
                    activationDate.equals(((Service) o).getActivationDate());
        } else {
            return false;
        }
    }

    @Override
    public Service clone() throws CloneNotSupportedException {
        return new Service(name, cost, serviceType, activationDate);
    }

    @Override
    public int compareTo(Service o) {
        if (o == null) {
            return 1;
        }

        return Double.compare(cost, o.cost);
    }
}