package blackjack.exception;

public class NotIntegerException extends BlackJackGameException {
    private static final String MESSAGE = "정수만 입력 가능합니다.";

    public NotIntegerException() {
        super(MESSAGE);
    }
}
