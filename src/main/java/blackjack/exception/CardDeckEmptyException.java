package blackjack.exception;

public class CardDeckEmptyException extends IllegalArgumentException {
    public CardDeckEmptyException(String message) {
        super(message);
    }
}
