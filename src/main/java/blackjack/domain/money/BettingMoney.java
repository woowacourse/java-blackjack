package blackjack.domain.money;

public class BettingMoney extends Money {

    private static final String BETTING_MONEY_NEGATIVE_EXCEPTION_MESSAGE = "배팅 금액은 음수일 수 없습니다.";

    public BettingMoney(final int value) {
        super(value);
        validateNegative(value);
    }

    private void validateNegative(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException(BETTING_MONEY_NEGATIVE_EXCEPTION_MESSAGE);
        }
    }
}
