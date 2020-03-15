package exception;

public class OverlapPlayerNameException extends RuntimeException {
    public OverlapPlayerNameException(String message) {
        super(message);
    }
}
