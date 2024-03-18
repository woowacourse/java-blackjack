package blackjack.exception;

public class InvalidPlayerCountException extends IllegalArgumentException {
    private final static String MESSAGE = "플레이어는 1명 이상 8명 이하여야 합니다.";

    public InvalidPlayerCountException() {
        super(MESSAGE);
    }
}
