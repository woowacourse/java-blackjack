package domain.result;

public class NotFoundResultException extends RuntimeException {
    public NotFoundResultException(String message) {
        super(message);
    }
}
