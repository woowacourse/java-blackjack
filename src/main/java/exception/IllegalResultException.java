package exception;

public class IllegalResultException extends RuntimeException {
    public IllegalResultException(final String message) {
        super(message);
    }
}