package blackjack.exception;

public class InvalidNameLengthException extends IllegalArgumentException {
    private final static String MESSAGE = "입력하신 이름 '%s'의 길이(%d)가 유효하지 않습니다. 이름의 길이는 1 이상 5 이하여야 합니다.";

    public InvalidNameLengthException(String name) {
        super(String.format(MESSAGE, name, name.length()));
    }
}
