package domain;

public class NegativeMoneyException extends IllegalArgumentException {
    public NegativeMoneyException(String message) {
        super(message);
    }
}
