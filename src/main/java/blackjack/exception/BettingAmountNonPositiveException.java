package blackjack.exception;

public class BettingAmountNonPositiveException extends IllegalArgumentException {
    private final static String MESSAGE = "베팅 금액은 양의 정수만 입력될 수 있습니다.";

    public BettingAmountNonPositiveException() {
        super(MESSAGE);
    }
}
