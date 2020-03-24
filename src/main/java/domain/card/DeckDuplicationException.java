package domain.card;

public class DeckDuplicationException extends IllegalArgumentException {
    public DeckDuplicationException() {
        super("중복되는 카드가 존재합니다.");
    }
}
