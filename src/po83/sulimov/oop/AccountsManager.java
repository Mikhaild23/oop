package po83.sulimov.oop;

import java.util.*;

public class AccountsManager implements Iterable<Account> {
    private int DEFAULT_SIZE = 8;
    private Account[] accounts;
    private int size;

    public AccountsManager(int size) {
        this.size = size;
        accounts = new Account[size];
    }

    public AccountsManager(Account[] accounts) {
        Objects.requireNonNull(accounts, "массив пустой");

        size = accounts.length;
        this.accounts = new Account[size()];
        System.arraycopy(accounts, 0, this.accounts, 0, size());
    }

    public boolean add(Account account) throws DuplicateAccountNumberException {
        Objects.requireNonNull(account, "аккаунт пустой");

        for (int i = 0; i < size; i++) {
            if (accounts[i] != null) {
                if (accounts[i].getNumber() == account.getNumber()) {
                    throw new DuplicateAccountNumberException("аккаунт с таким номером уже существует");
                }
            }
        }

        for (int i = 0; i < size(); i++) {
            if (accounts[i] == null) {
                accounts[i] = account;
                return true;
            }
        }
        doubleArraySize();
        return add(account);
    }

    public boolean add(int index, Account account) throws DuplicateAccountNumberException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("индекс вне допустимого диапозона");
        }

        Objects.requireNonNull(account, "аккаунт пустой");

        for (int i = 0; i < size; i++) {
            if (accounts[i] != null) {
                if (accounts[i].getNumber() == account.getNumber()) {
                    throw new DuplicateAccountNumberException("аккаунт с таким номером уже существует");
                }
            }
        }

        if (accounts[index] == null) {
            accounts[index] = account;
            return true;
        } else {
            return false;
        }
    }

    public Account get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("индекс вне допустимого диапозона");
        }

        return accounts[index];
    }

    public Account getAccount(long accountNumber) {
        if (!AbstractAccount.checkAccountNumber(accountNumber)) {
            throw new IllegalAccountNumber("номер не соответствует формату");
        }

        for (Account account : this) {
            if (account != null) {
                if (account.getNumber() == accountNumber) {
                    return account;
                }
            }
        }

        throw new NoSuchElementException("элемент не найден");
    }

    public boolean hasAccount(long accountNumber) {
        if (!AbstractAccount.checkAccountNumber(accountNumber)) {
            throw new IllegalAccountNumber("номер не соответствует формату");
        }

        for (int i = 0; i < size; i++) {
            if (accounts[i] != null) {
                if (accounts[i].getNumber() == accountNumber) {
                    return true;
                }
            }
        }

        return false;
    }

    public Account set(int index, Account account) throws DuplicateAccountNumberException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("индекс вне допустимого диапозона");
        }

        Objects.requireNonNull(account, "аккаунт пустой");

        for (int i = 0; i < size; i++) {
            if (accounts[i] != null) {
                if (accounts[i].getNumber() == account.getNumber() && i != index) {
                    throw new DuplicateAccountNumberException("аккаунт с таким номером уже существует");
                }
            }
        }

        Account changedAccount = accounts[index];
        accounts[index] = account;
        return changedAccount;
    }

    public Account remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("индекс вне допустимого диапозона");
        }

        Account removedAccount = accounts[index];
        System.arraycopy(accounts, index + 1, accounts, index, size - index - 1);
        accounts[size - index - 1] = null;
        return removedAccount;
    }

    public Account remove(long accountNumber) {
        if (!AbstractAccount.checkAccountNumber(accountNumber)) {
            throw new IllegalAccountNumber("номер не соответствует формату");
        }

        for (int i = 0; i < size; i++) {
            if (accounts[i] != null) {
                if (accounts[i].getNumber() == accountNumber) {
                    return remove(i);
                }
            }
        }

        throw new NoSuchElementException("элемент не найден");
    }

    public int size() {
        return size;
    }

    public Account[] getAccounts() {
        Account[] result = new Account[size()];
        System.arraycopy(accounts, 0, result, 0, size());
        return result;
    }

    public Set<Account> getAccounts(ServiceTypes serviceType) {
        Objects.requireNonNull(serviceType, "тип аккаунта пустой");

        HashSet<Account> result = new HashSet<>();

        for (Account account : this) {
            if (account != null) {
                if (account.getTariff() != null) {
                    Service[] buffer = (Service[]) account.getTariff().toArray();
                    for (Service service : buffer) {
                        if (service != null) {
                            if (service.getServiceType() == serviceType) {
                                result.add(account);
                                break;
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    public Set<Account> getAccounts(String serviceName) {
        Objects.requireNonNull(serviceName, "название услуги пустое");

        HashSet<Account> result = new HashSet<>();

        for (Account account : this) {
            if (account != null) {
                if (account.getTariff().hasService(serviceName)) {
                    result.add(account);
                }
            }
        }

        return result;
    }

    public List<Account> getIndividualAccounts() {
        ArrayList<Account> result = new ArrayList<>();

        for (Account account : this) {
            if (account != null) {
                if (account.getClass() == IndividualAccount.class) {
                    result.add(account);
                }
            }
        }

        return result;
    }

    public List<Account>  getEntityAccounts() {
        LinkedList<Account> result = new LinkedList<>();

        for (Account account : this) {
            if (account != null) {
                if (account.getClass() == EntityAccount.class) {
                    result.add(account);
                }
            }
        }

        return result;
    }

    public Tariff getTariff(long accountNumber) {
        if (!AbstractAccount.checkAccountNumber(accountNumber)) {
            throw new IllegalAccountNumber("номер не соответствует формату");
        }

        for (Account account : this) {
            if (account != null) {
                if (account.getNumber() == accountNumber) {
                    return account.getTariff();
                }
            }
        }

        throw new NoSuchElementException("элемент не найден");
    }

    public Tariff setTariff(long accountNumber, Tariff tariff) {
        if (!AbstractAccount.checkAccountNumber(accountNumber)) {
            throw new IllegalAccountNumber("номер не соответствует формату");
        }

        Objects.requireNonNull(tariff, "тариф пустой");

        Tariff result = null;
        for (Account account : this) {
            if (account != null) {
                if (account.getNumber() == accountNumber) {
                    result = account.getTariff();
                    account.setTariff(tariff);
                    return result;
                }
            }
        }

        throw new NoSuchElementException("элемент не найден");
    }

    private void doubleArraySize() {
        Account[] result = new Account[size * 2];
        System.arraycopy(accounts, 0, result, 0, size());
        size *= 2;
        accounts = result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (accounts[i] != null) {
                result.append(accounts[i].toString());
            }
        }
        return result.toString();
    }

    public boolean remove(Account account) {
        Objects.requireNonNull(account, "аккаунт пустой");

        for (int i = 0; i < size; i++) {
            if (accounts[i] == null) {
                if (account == null) {
                    return true;
                }
            } else if (accounts[i].equals(account)) {
                accounts[i] = null;
                return true;
            }
        }
        return false;
    }

    public int indexOf(Account account) {
        Objects.requireNonNull(account, "аккаунт пустой");

        for (int i = 0; i < size; i++) {
            if (accounts[i] == null) {
                if (account == null) {
                    return i;
                }
            } else if (accounts[i].equals(account)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<Account> iterator() {
        return new AccountIterator();
    }

    private class AccountIterator implements Iterator<Account> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size() - 1;
        }

        @Override
        public Account next() {
            if (hasNext()) {
                return get(index++);
            } else {
                throw new NoSuchElementException("итератор не нашёл следующий элемент");
            }
        }
    }
}