package blackjack.exception;

public class CardDeckDuplicatedException extends RuntimeException {
    public CardDeckDuplicatedException(String message) {
        super(message);
    }
}
