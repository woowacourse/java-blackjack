package blakcjack.exception;

public class NegativeNumericException extends RuntimeException {
    public NegativeNumericException(final int value) {
        super(String.format("음수가 올 수 없습니다 (%d)", value));
    }
}
