package domain;

public class IllegalResponseException extends IllegalArgumentException {
    public IllegalResponseException(String message) {
        super(message);
    }
}
