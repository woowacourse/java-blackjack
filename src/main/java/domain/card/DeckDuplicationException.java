package domain.card;

public class DeckDuplicationException extends IllegalArgumentException {
    public DeckDuplicationException(String message) {
        super(message);
    }
}
