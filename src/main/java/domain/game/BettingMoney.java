package domain.game;

import java.util.Objects;

public class BettingMoney {
    private static final int MIN_MONEY = 0;
    private static final int MAX_MONEY = 100_000_000;

    private final int money;

    private BettingMoney(final int money) {
        this.money = money;
    }

    public static BettingMoney create(final String money) {
        int validMoney = validType(money);
        validateRange(validMoney);
        return new BettingMoney(validMoney);
    }

    public static BettingMoney zero() {
        return new BettingMoney(0);
    }

    private static int validType(final String money) {
        int validMoney;
        try {
            validMoney = Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("베팅 금액은 정수 값이어야 합니다.");
        }
        return validMoney;
    }

    private static void validateRange(final int money) {
        if (money <= MIN_MONEY || money > MAX_MONEY) {
            throw new IllegalArgumentException("베팅 금액은 0원 초과, 100,000,000원 이하여야 합니다.");
        }
    }

    public BettingMoney add(final BettingMoney bettingMoney) {
        return new BettingMoney(this.money + bettingMoney.money);
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) return true;
        if (target == null || getClass() != target.getClass()) return false;
        final BettingMoney that = (BettingMoney) target;
        return money == that.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
