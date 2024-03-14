package domain.betting;

import java.util.Objects;

public final class Money {

    private static final int MONEY_MULTIPLE = 1_000;

    private static final int MINIMUM_BET_MONEY = 1_000;
    private static final int MAXIMUM_BET_MONEY = 1_000_000_000;

    private static final double WIN_EARNING_RATE = 1.0;
    private static final double TIE_EARNING_RATE = 0.0;
    private static final double LOSE_EARNING_RATE = -1.0;
    private static final double BLACKJACK_EARNING_RATE = 1.5;
    private static final double BOTH_BLACKJACK_EARNING_RATE = 1.0;

    private final int value;

    private Money(int value) {
        this.value = value;
    }

    public static Money bet(int value) {
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

    public Money findPlayerProfitWhenPlayerWin() {
        return new Money((int) (value * WIN_EARNING_RATE));
    }

    public Money findPlayerProfitWhenTie() {
        return new Money((int) (value * TIE_EARNING_RATE));
    }

    public Money findPlayerProfitWhenPlayerLose() {
        return new Money((int) (value * LOSE_EARNING_RATE));
    }

    public Money findPlayerProfitWhenPlayerBlackjack() {
        return new Money((int) (value * BLACKJACK_EARNING_RATE));
    }

    public Money findPlayerProfitWhenBothBlackjack() {
        return new Money((int) (value * BOTH_BLACKJACK_EARNING_RATE));
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
