package blackjack.exception;

public class GamerDuplicateException extends RuntimeException {

    private static final String MESSAGE = "게이머 이름이 중복이 되었습니다.";

    public GamerDuplicateException() {
        super(MESSAGE);
    }
}
