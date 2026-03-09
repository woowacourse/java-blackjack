package domain.exception;

public class EmptyDeckException extends RuntimeException {
    public EmptyDeckException() {
        super("덱에 카드가 더 이상 없습니다.");
    }
}
