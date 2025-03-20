package betting;

public class BettingMoney {

    public static final int MINIMUM_MONEY = 0;
    public static final int MAXIMUM_MONEY = 100_000;

    private final int money;

    public BettingMoney(final int money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(int money) {
        validateMoneyOverZero(money);
        validateMoneyUnderMaximumMoney(money);
    }

    private void validateMoneyOverZero(int money) {
        if (money <= MINIMUM_MONEY) {
            throw new IllegalArgumentException(String.format("[ERROR] 배팅 금액은 %,d원을 초과해야 합니다.", MINIMUM_MONEY));
        }
    }

    private void validateMoneyUnderMaximumMoney(int money) {
        if (money > MAXIMUM_MONEY) {
            throw new IllegalArgumentException(String.format("[ERROR] 배팅 금액은 %,d원을 초과할 수 없습니다.", MAXIMUM_MONEY));
        }
    }

    public int getMoney() {
        return money;
    }
}
