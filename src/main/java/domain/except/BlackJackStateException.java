package domain.except;

public class BlackJackStateException extends IllegalStateException {
    public BlackJackStateException(String message) {
        super(message);
    }
}
