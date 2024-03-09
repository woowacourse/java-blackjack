package blackjack.exception;

public class InvalidHitCommandException extends IllegalArgumentException {
    private final static String MESSAGE = "'%s'는 유효하지 않은 입력입니다. 'y' 혹은 'n'만 입력될 수 있습니다.";

    public InvalidHitCommandException(String input) {
        super(String.format(MESSAGE, input));
    }
}
