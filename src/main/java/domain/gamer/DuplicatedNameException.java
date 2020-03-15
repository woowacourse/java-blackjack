package domain.gamer;

public class DuplicatedNameException extends IllegalArgumentException {
    public DuplicatedNameException(String message) {
        super(message);
    }
}
