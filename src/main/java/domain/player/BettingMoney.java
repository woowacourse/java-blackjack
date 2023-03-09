package domain.player;

import java.math.BigDecimal;

public class BettingMoney {
    private static final String INVALID_RANGE = "배팅 금액은 100원 이상, 1억 이하여야 합니다.";
    private static final String INVALID_UNIT = "배팅 금액은 100원 단위입니다.";
    private static final BigDecimal MINIMUM = BigDecimal.valueOf(100);
    private static final BigDecimal MAXIMUM = BigDecimal.valueOf(100_000_000);

    private final BigDecimal money;

    private BettingMoney(int money) {
        this.money = BigDecimal.valueOf(money);
    }

    private BettingMoney(BigDecimal money) {
        this.money = money;
    }

    public static BettingMoney from(int money) {
        validate(money);

        return new BettingMoney(money);
    }

    private static void validate(int money) {
        validateRange(money);
        validateUnit(money);
    }

    private static void validateRange(int money) {
        if (money < MINIMUM.intValue() || money > MAXIMUM.intValue()) {
            throw new IllegalArgumentException(INVALID_RANGE);
        }
    }

    private static void validateUnit(int money) {
        BigDecimal remainder = BigDecimal.valueOf(money).remainder(MINIMUM);

        if (!BigDecimal.ZERO.equals(remainder)) {
            throw new IllegalArgumentException(INVALID_UNIT);
        }
    }

    public BettingMoney applyRatio(double ratio) {
        return new BettingMoney(money.multiply(BigDecimal.valueOf(ratio)));
    }

    public BettingMoney calculateProfit(BettingMoney currentMoney) {
        BigDecimal profit = (currentMoney.money).subtract(this.money);

        return new BettingMoney(profit);
    }

    public int getMoney() {
        return money.intValue();
    }
}
