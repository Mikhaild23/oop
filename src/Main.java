
import po83.sulimov.oop.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        test();

    }
    public static void test()
    {
        try {
            AccountsManager accountsManager = new AccountsManager(10);
            Account a1 = new IndividualAccount(1000000000001L, new Person("Грут", "Грут"));
            Account a2 = new IndividualAccount(999999999999999L, new Person("Грут", "Грут"), new IndividualsTariff(), LocalDate.now());
            a1.getTariff().add(new Service());
            a2.getTariff().add(new Service());
            System.out.println(a1.equals(a2));

            Account a3 = new EntityAccount(1000000000011L, "Mikhail");
            a3.setTariff(new EntityTariff());
            a3.getTariff().add(new Service("1", 10, ServiceTypes.MAIL, LocalDate.now()));
            a3.getTariff().add(new Service("2", 20, ServiceTypes.PHONE, LocalDate.now()));
            a3.getTariff().add(new Service("3", 30, ServiceTypes.STORAGE, LocalDate.now()));
            System.out.println(a3.equals(a1));
            System.out.println(a3);

            System.out.println();

            accountsManager.add(a1);
            accountsManager.add(a2);
            accountsManager.add(a3);

            for (Account account : accountsManager) {
                System.out.println(account);
            }

            System.out.println();

            System.out.println(a2.getTariff().indexOf(new Service()));
            System.out.println(a2.getTariff().remove(new Service()));
            System.out.println(a2.getTariff().indexOf(new Service()));

            System.out.println();

            Account a4 = new EntityAccount(1000000000002L, "Ракета");
            a4.getTariff().add(new Service("2", 20, ServiceTypes.INTERNET, LocalDate.now()));
            a4.getTariff().add(new Service("1", 10, ServiceTypes.INTERNET, LocalDate.now()));
            a4.getTariff().add(new Service("2", 30, ServiceTypes.INTERNET, LocalDate.now()));
            a4.getTariff().add(new Service("1", 10, ServiceTypes.INTERNET, LocalDate.now()));
            a4.getTariff().add(new Service("2", 5, ServiceTypes.INTERNET, LocalDate.now()));

            System.out.println(a4.getTariff());



            EntityTariff entityTariff = new EntityTariff();
            entityTariff.add(new Service("2", 20, ServiceTypes.INTERNET, LocalDate.now()));
            entityTariff.add(new Service("1", 10, ServiceTypes.INTERNET, LocalDate.now()));
            entityTariff.add(new Service("2", 30, ServiceTypes.INTERNET, LocalDate.now()));
            entityTariff.add(new Service("1", 10, ServiceTypes.INTERNET, LocalDate.now()));
            entityTariff.add(new Service("2", 5, ServiceTypes.INTERNET, LocalDate.now()));

            System.out.println(a4.getTariff().lastIndexOf(new Service("1", 10, ServiceTypes.INTERNET, LocalDate.now())));

            System.out.println();

            Iterator<Service> iterator = entityTariff.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }

            System.out.println();

            for (Service service : entityTariff) {
                System.out.println(service);
            }

            System.out.println();

            IndividualsTariff individualsTariff = new IndividualsTariff();
            individualsTariff.add(new Service("2", 20, ServiceTypes.INTERNET, LocalDate.now()));
            individualsTariff.add(new Service("1", 5, ServiceTypes.INTERNET, LocalDate.now()));
            individualsTariff.add(new Service("2", 30, ServiceTypes.INTERNET, LocalDate.now()));
            individualsTariff.add(new Service("1", 10, ServiceTypes.INTERNET, LocalDate.now()));
            individualsTariff.add(new Service("2", 5, ServiceTypes.INTERNET, LocalDate.now()));

            Service[] services1 = new Service[2];
            services1[0] = new Service("2", 30, ServiceTypes.INTERNET, LocalDate.now());
            services1[1] =  new Service("1", 10, ServiceTypes.INTERNET, LocalDate.now());

            individualsTariff.retainAll(Arrays.asList(services1));

            for (Service service : individualsTariff) {
                System.out.println(service);
            }

            System.out.println(individualsTariff.isEmpty());
            individualsTariff.clear();
            System.out.println(individualsTariff.isEmpty());

            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
