package blackjack.exception;

public class InvalidHitCommandException extends IllegalArgumentException {
    private final static String MESSAGE = "y 혹은 n만 입력될 수 있습니다.";

    public InvalidHitCommandException() {
        super(MESSAGE);
    }
}
