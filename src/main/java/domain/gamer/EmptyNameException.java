package domain.gamer;

public class EmptyNameException extends IllegalArgumentException {
    public EmptyNameException(String message) {
        super(message);
    }
}
