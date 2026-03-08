package domain.exception;

public class DeckIsEmptyException extends RuntimeException {
    public DeckIsEmptyException() {
        super("덱에 카드가 더 이상 없습니다.");
    }
}
