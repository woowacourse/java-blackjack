package domain;

public class BetAmount {
    private static final int MINIMUM = 10;
    private static final int MAXIMUM = 100_000_000;

    private final int money;

    public BetAmount(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money < MINIMUM || money > MAXIMUM) {
            throw new IllegalArgumentException("배팅 금액은 10원 이상, 1억 이하여야 합니다.");
        }
    }
}
