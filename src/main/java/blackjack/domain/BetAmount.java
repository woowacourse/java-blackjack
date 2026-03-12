package blackjack.domain;

public class BetAmount {
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
        if (money <= 0) {
            throw new IllegalArgumentException("배팅 금액은 최소 0원 이상이어야 합니다.");
        }
    }

    private void validateMoneyUnderMaximum(int money) {
        if (money > 100000) {
            throw new IllegalArgumentException("배팅 금액은 최대 100000원 이하이어야 합니다.");
        }
    }
}