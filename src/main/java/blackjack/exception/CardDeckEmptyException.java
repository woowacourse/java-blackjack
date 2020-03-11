package blackjack.exception;

public class CardDeckEmptyException extends RuntimeException {
    public CardDeckEmptyException(String message) {
        super(message);
    }
}
