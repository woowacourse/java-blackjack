package blackjack.exception;

public class InvalidNameLengthException extends IllegalArgumentException {
    private final static String MESSAGE = "이름의 길이는 1 이상 5 이하여야 합니다.";

    public InvalidNameLengthException() {
        super(MESSAGE);
    }
}
