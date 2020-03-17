package domain.gamer;

public class NameDuplicationException extends IllegalArgumentException {
    public NameDuplicationException(String message) {
        super(message);
    }
}
