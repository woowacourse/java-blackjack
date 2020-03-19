package exception;

public class PlayerNamesOverlapException extends RuntimeException {
    public PlayerNamesOverlapException(String message) {
        super(message);
    }
}