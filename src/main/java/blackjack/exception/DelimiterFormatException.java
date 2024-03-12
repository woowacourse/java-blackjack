package blackjack.exception;

public class DelimiterFormatException extends IllegalArgumentException {
    private final static String MESSAGE = "시작이나 끝에 구분자를 입력할 수 없습니다.";

    public DelimiterFormatException() {
        super(MESSAGE);
    }
}
