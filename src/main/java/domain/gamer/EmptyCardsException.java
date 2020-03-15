package domain.gamer;

public class EmptyCardsException extends RuntimeException {
    public EmptyCardsException(String message) {
        super(message);
    }
}
