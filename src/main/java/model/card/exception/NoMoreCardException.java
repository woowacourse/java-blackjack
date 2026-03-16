package model.card.exception;

public class NoMoreCardException extends IllegalStateException {
    public NoMoreCardException(Throwable cause) {
        super("덱이 비어서 카드를 드로우 할 수 없습니다.", cause);
    }
}
