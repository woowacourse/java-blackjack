package blackjack.exception;

public class NonAlphabeticNameException extends IllegalArgumentException {
    private final static String MESSAGE = "이름 '%s'에는 영문, 한글이 아닌 문자가 들어있습니다. 영문과 한글만 사용해 주세요.";

    public NonAlphabeticNameException(String name) {
        super(String.format(MESSAGE, name));
    }
}
