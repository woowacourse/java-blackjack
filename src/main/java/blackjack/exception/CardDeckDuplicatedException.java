package blackjack.exception;

public class CardDeckDuplicatedException extends IllegalArgumentException {
    public CardDeckDuplicatedException(String message) {
        super(message);
    }
}
