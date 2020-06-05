package po83.sulimov.oop;

public class IllegalAccountNumber extends RuntimeException{
    public IllegalAccountNumber() {
    }

    public IllegalAccountNumber(String message) {
        super(message);
    }
}
