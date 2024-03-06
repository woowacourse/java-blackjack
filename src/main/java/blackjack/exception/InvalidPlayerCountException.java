package blackjack.exception;

public class InvalidPlayerCountException extends IllegalArgumentException {
    private final static String MESSAGE = "플레이어는 한 명 이상이어야 합니다.";

    public InvalidPlayerCountException() {
        super(MESSAGE);
    }
}
