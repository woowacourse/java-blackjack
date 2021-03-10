package blackjack.domain;

public class BettingMoney {
    private static final String NEGATIVE_MONEY_ERROR_MESSAGE = "베팅 금액은 0보다 커야합니다.";

    private final int money;

    public BettingMoney(int money) {
        validateNegative(money);
        this.money = money;
    }

    private void validateNegative(int money) {
        if (money < 0) {
            throw new IllegalArgumentException(NEGATIVE_MONEY_ERROR_MESSAGE);
        }
    }
}
