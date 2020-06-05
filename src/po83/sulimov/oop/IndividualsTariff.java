package po83.sulimov.oop;

import java.util.*;

public class IndividualsTariff implements Tariff, Cloneable {
    private int DEFAULT_SIZE = 8;
    private Service[] services;
    private int size;

    public IndividualsTariff() {
        size = DEFAULT_SIZE;
        services = new Service[size];
    }

    public IndividualsTariff(int size) {
        this.size = size;
        services = new Service[size];
    }

    public IndividualsTariff(Service[] services) {
        Objects.requireNonNull(services, "массив пустой");

        size = services.length;
        this.services = new Service[size()];
        System.arraycopy(services, 0, this.services, 0, size());
    }

    public boolean add(Service service) {
        Objects.requireNonNull(service, "услуга пустая");

        for (int i = 0; i < size(); i++) {
            if (services[i] == null) {
                services[i] = service;
                return true;
            }
        }
        doubleArraySize();
        return add(service);
    }

    @Override
    public boolean addAll(Collection<? extends Service> c) {
        for (Object o : c) {
            for (int i = 0; i < size; i++) {
                if (services[i] == null) {
                    services[i] = (Service) o;
                    break;
                }

                if (i == size - 1) {
                    doubleArraySize();
                    i--;
                }
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = false;
        for (Object o : c) {
            for (int i = 0; i < size; i++) {
                if (services[i] != null) {
                    if (services[i].equals(o)) {
                        services[i] = null;
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean result = false;
        for (int i = 0; i < size; i++) {
            boolean ok = false;
            for (Object o : c) {
                if (services[i] != null) {
                    if (services[i].equals(o)) {
                        ok = true;
                        break;
                    }
                }
            }

            if (ok) {
                continue;
            }

            services[i] = null;
            result = true;
        }
        return result;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            services[i] = null;
        }
    }

    public boolean add(int index, Service service) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("индекс вне допустимого диапозона");
        }

        Objects.requireNonNull(service, "услуга пустая");

        if (services[index] == null) {
            services[index] = service;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Service get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("индекс вне допустимого диапозона");
        }

        return services[index];
    }

    @Override
    public Collection<Service> getServices(ServiceTypes serviceType) {
        Objects.requireNonNull(serviceType, "тип услуги пустой");

        LinkedList<Service> result = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            if (services[i] != null) {
                if (services[i].getServiceType() == serviceType) {
                    result.add(services[i]);
                }
            }
        }

        return result;
    }

    public Service set(int index, Service service) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("индекс вне допустимого диапозона");
        }

        Objects.requireNonNull(service, "услуга пустая");

        Service changedService = services[index];
        services[index] = service;
        return changedService;
    }

    public Service remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("индекс вне допустимого диапозона");
        }

        Service removedService = services[index];
        System.arraycopy(services, index + 1, services, index, size - index - 1);
        services[size - index - 1] = null;
        return removedService;
    }

    @Override
    public int lastIndexOf(Service service) {
        Objects.requireNonNull(service, "услуга пустая");

        for (int i = size - 1; i >= 0; i--) {
            if (services[i] != null) {
                if (services[i].equals(service)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        for (Service service : this) {
            if (service != null) {
                return false;
            }
        }
        return true;
    }

    private void doubleArraySize() {
        Service[] result = new Service[size() * 2];
        System.arraycopy(services, 0, result, 0, size());
        size *= 2;
        services = result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("services:");
        for (int i = 0; i < size; i++) {
            if (services[i] != null) {
                result.append(String.format("\n%s", services[i]));
            }
        }
        return result.toString();
    }

    @Override
    public int hashCode() {
        int result = 31;
        for (int i = 0; i < size; i++) {
            if (services[i] != null) {
                result ^= services[i].hashCode();
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this.getClass() == o.getClass()) {
            IndividualsTariff tariff = (IndividualsTariff) o;

            boolean ok;
            for (int i = 0; i < size; i++) {
                ok = false;
                for (int j = 0; j < tariff.size(); j++) {
                    if (services[i] == null) {
                        if (tariff.get(j) == null) {
                            ok = true;
                            break;
                        }
                    } else if (services[i].equals(tariff.get(j))) {
                        ok = true;
                        break;
                    }
                }

                if (!ok) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public Tariff clone() throws CloneNotSupportedException {
        return new IndividualsTariff(services);
    }

    @Override
    public Iterator<Service> iterator() {
        return new ServiceIterator();
    }

    private class ServiceIterator implements Iterator<Service> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size() - 1;
        }

        @Override
        public Service next() {
            if (hasNext()) {
                return get(index++);
            } else {
                throw new NoSuchElementException("итератор не нашёл следующий элемент");
            }
        }
    }
}
