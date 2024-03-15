package blackjack.exception;

public class BettingAmountFormatException extends IllegalArgumentException{
    private final static String MESSAGE = "베팅 금액은 정수만 입력될 수 있습니다.";

    public BettingAmountFormatException() {
        super(MESSAGE);
    }
}
