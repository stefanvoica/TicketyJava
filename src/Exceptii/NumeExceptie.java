package Exceptii;

public class NumeExceptie extends RuntimeException {
    public NumeExceptie() {
        super("Numele nu poate fi null!");
    }
}
