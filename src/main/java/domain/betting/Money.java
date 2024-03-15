package domain.betting;

import domain.game.PlayerResult;
import java.util.Objects;

public final class Money {

    private static final int MONEY_MULTIPLE = 1_000;

    private static final int MINIMUM_BET_MONEY = 1_000;
    private static final int MAXIMUM_BET_MONEY = 1_000_000_000;

    private final int value;

    private Money(int value) {
        this.value = value;
    }

    public static Money valueOf(int value) {
        return new Money(value);
    }

    public static Money betValueOf(int value) {
        validateRange(value);
        validateMultiple(value);
        return new Money(value);
    }

    private static void validateRange(int value) {
        if (value < MINIMUM_BET_MONEY || MAXIMUM_BET_MONEY < value) {
            throw new IllegalArgumentException(
                String.format("[ERROR] %,d원 이상 %,d원 이하로 베팅해 주세요.", MINIMUM_BET_MONEY, MAXIMUM_BET_MONEY));
        }
    }

    private static void validateMultiple(int value) {
        if (value % MONEY_MULTIPLE != 0) {
            throw new IllegalArgumentException(String.format("[ERROR] %,d원 단위로 베팅해 주세요.", MONEY_MULTIPLE));
        }
    }

    public Money add(Money amount) {
        return new Money(value + amount.value);
    }

    public Money calculateProfit(PlayerResult playerResult) {
        return new Money((int) (value * playerResult.getProfitRate()));
    }

    public int toInt() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (Money) obj;
        return this.value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Money[" +
            "value=" + value + ']';
    }
}
