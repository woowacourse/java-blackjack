package blackjack.exception;

public class BettingPriceOutOfBoundException extends IllegalArgumentException {
    private final static String MESSAGE = "현재 입력된 베팅 금액은 %d원으로, 적절하지 않습니다. 1,000원에서 100,000원 사이의 금액을 입력해 주세요.";

    public BettingPriceOutOfBoundException(int bettingPrice) {
        super(String.format(MESSAGE, bettingPrice));
    }
}