package domain;

public class NegativeScoreException extends IllegalArgumentException {
    public NegativeScoreException(String message) {
        super(message);
    }
}
