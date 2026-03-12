package blackjack.domain;

public class BetAmount {
    private static final int MINIMUM_AMOUNT = 1;
    private static final int MAXIMUM_AMOUNT = 100000;
    private final int money;

    private BetAmount(int money) {
        validate(money);
        this.money = money;
    }

    public static BetAmount of(int money) {
        return new BetAmount(money);
    }

    private void validate(int money) {
        validateMoneyOverMinimum(money);
        validateMoneyUnderMaximum(money);
    }

    private void validateMoneyOverMinimum(int money) {
        if (money < MINIMUM_AMOUNT) {
            throw new IllegalArgumentException("배팅 금액은 최소 0원 이상이어야 합니다.");
        }
    }

    private void validateMoneyUnderMaximum(int money) {
        if (money > MAXIMUM_AMOUNT) {
            throw new IllegalArgumentException("배팅 금액은 최대 100000원 이하이어야 합니다.");
        }
    }
}