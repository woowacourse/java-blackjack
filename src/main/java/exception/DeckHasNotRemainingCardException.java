package exception;

public class DeckHasNotRemainingCardException extends RuntimeException {

    public static final String MESSAGE = "[ERROR] 뽑을 카드가 더 이상 존재하지 않습니다.";

    public DeckHasNotRemainingCardException() {
        super(MESSAGE);
    }
}
