package domain.player;

import java.util.Objects;

public class BettingMoney {
    private static final String INVALID_RANGE = "배팅 금액은 100원 이상, 1억 이하여야 합니다.";
    private static final String INVALID_UNIT = "배팅 금액은 100원 단위입니다.";
    private static final int MINIMUM = 100;
    private static final int MAXIMUM = 100_000_000;

    private final int money;

    private BettingMoney(int money) {
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
        if (money < MINIMUM || money > MAXIMUM) {
            throw new IllegalArgumentException(INVALID_RANGE);
        }
    }

    private static void validateUnit(int money) {
        if (money % MINIMUM != 0) {
            throw new IllegalArgumentException(INVALID_UNIT);
        }
    }

    public BettingMoney applyRatio(double ratio) {
        return new BettingMoney((int) (money * ratio));
    }

    public int getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BettingMoney betAmount = (BettingMoney) o;
        return money == betAmount.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
