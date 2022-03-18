package blackjack.domain.game;

import java.util.Objects;

public class BettingMoney {

    private static final BettingMoney zeroBettingMoney = new BettingMoney("0");

    private static final int BETTING_MONEY_UNIT = 10;

    private final int value;

    private BettingMoney(final String value) {
        this.value = Integer.parseInt(value);
    }

    public BettingMoney(final int value) {
        validateMinValue(value);
        validateUnit(value);
        this.value = value;
    }

    private void validateMinValue(final int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("배팅 금액은 양수여야 합니다.");
        }
    }

    private void validateUnit(final int value) {
        if (value % BETTING_MONEY_UNIT != 0) {
            throw new IllegalArgumentException("배팅 금액의 단위는 10입니다.");
        }
    }

    public int getProfit(final double profitRate) {
        return (int) (value * profitRate);
    }

    public static BettingMoney getZeroBettingMoney() {
        return zeroBettingMoney;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BettingMoney that = (BettingMoney) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
