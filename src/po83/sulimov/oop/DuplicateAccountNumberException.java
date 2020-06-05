package po83.sulimov.oop;

public class DuplicateAccountNumberException extends Exception {
    public DuplicateAccountNumberException() {
        super();
    }

    public DuplicateAccountNumberException(String message) {
        super(message);
    }

    public DuplicateAccountNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}