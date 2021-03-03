package blackjack.exception;

public class CardDuplicateException extends RuntimeException {

    private static final String MESSAGE = "카드가 중복이 되었습니다.";

    public CardDuplicateException() {
        super(MESSAGE);
    }
}
