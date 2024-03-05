package blackjack.exception;

public class NonAlphabeticNameException extends IllegalArgumentException {
    private final static String MESSAGE = "이름에 알파벳이 아닌 문자가 들어있습니다.";

    public NonAlphabeticNameException() {
        super(MESSAGE);
    }
}
