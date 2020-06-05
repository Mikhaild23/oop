package po83.sulimov.oop;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public interface Tariff extends Iterable<Service>, Collection<Service> {
    boolean add(Service service);

    boolean add(int index, Service service);

    @Override
    default boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            boolean ok = false;
            for (Service service : this) {
                if (service.equals(o)) {
                    ok = true;
                    break;
                }
            }

            if (ok) {
                continue;
            }

            return false;
        }
        return true;
    }

    Service get(int index);

    default Service get(String serviceName) {
        Objects.requireNonNull(serviceName, "название услуги пустое");

        for (Service service : this) {
            if (service != null) {
                if (service.getName().equals(serviceName)) {
                    return service;
                }
            }
        }

        throw new NoSuchElementException("Услуга не найдена");
    }

    Collection<Service> getServices(ServiceTypes serviceType);

    default boolean hasService(String serviceName) {
        Objects.requireNonNull(serviceName, "название услуги пустое");

        for (Service service : this) {
            if (service != null) {
                if (service.getName().equals(serviceName)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    default boolean contains(Object o) {
        Objects.requireNonNull(o, "объект пустой");

        for (Service service : this) {
            if (service != null) {
                if (service.equals(o)) {
                    return true;
                }
            }
        }

        return false;
    }

    Service set(int index, Service service);

    Service remove(int index);

    default Service remove(String serviceName) {
        Objects.requireNonNull(serviceName, "название услуги пустое");

        int index = 0;
        for (Service service : this) {
            if (service != null) {
                if (service.getName().equals(serviceName)) {
                    return remove(index);
                }
            }
            index++;
        }

        throw new NoSuchElementException("Услуга не найдена");
    }

    default boolean remove(Object o) {
        Objects.requireNonNull(o, "услуга пустая");

        int index = 0;
        for (Service buffer : this) {
            if (buffer != null) {
                if (buffer.equals(o)) {
                    remove(index);
                    return true;
                }
            }
            index++;
        }

        return false;
    }

    default int indexOf(Service service) {
        Objects.requireNonNull(service, "услуга пустая");

        int index = 0;
        for (Service buffer : this) {
            if (buffer != null) {
                if (buffer.equals(service)) {
                    return index;
                }
            }
            index++;
        }
        return -1;
    }

    int lastIndexOf(Service service);

    int size();

    @Override
    default Object[] toArray() {
        Service[] result = new Service[size()];

        int index = 0;
        for (Service service : this) {
            result[index] = service;
            index++;
        }

        return result;
    }

    @Override
    default <T> T[] toArray(T[] a) {
        return (T[])toArray();
    }

    default List<Service> sortedServicesByCost() {
        ArrayList<Service> result = new ArrayList<>();

        for (Service service : this) {
            if (service != null) {
                result.add(service);
            }
        }

        result.sort(Service::compareTo);

        return result;
    }

    default double cost() {
        double result = 0;

        for (Service service : this) {
            if (service != null) {
                if (Period.between(service.getActivationDate(), LocalDate.now()).toTotalMonths() < 1) {
                    result += service.getCost() *
                            Period.between(service.getActivationDate(), LocalDate.now()).getDays() /
                            LocalDate.now().lengthOfMonth();
                } else {
                    result += service.getCost();
                }
            }
        }

        return result;
    }

    String toString();

    int hashCode();

    boolean equals(Object o);

    public Tariff clone() throws CloneNotSupportedException;
}
