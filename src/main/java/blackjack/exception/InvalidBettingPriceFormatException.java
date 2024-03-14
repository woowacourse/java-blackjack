package blackjack.exception;

public class InvalidBettingPriceFormatException extends IllegalArgumentException {
    private final static String MESSAGE = "입력하신 '%s'는 유효하지 않습니다. 정수로 변환할 수 없는 베팅 금액을 입력해 주세요.";

    public InvalidBettingPriceFormatException(String input) {
        super(String.format(MESSAGE, input));
    }
}
