package blackjack.exception;

public class InvalidBetMoneyException extends CustomException {
    private static final String MESSAGE = "배팅 금액은 %d원 이상 %d원 이하만 가능합니다.";

    public InvalidBetMoneyException(int max, int min) {
        super(String.format(MESSAGE, max, min));
    }
}
