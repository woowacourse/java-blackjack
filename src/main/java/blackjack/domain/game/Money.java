package blackjack.domain.game;

import blackjack.domain.card.Result;
import java.math.BigDecimal;
import java.util.Objects;

public final class Money {
    public static final Money ZERO = new Money(0);
    private static final int INITIAL_BET_LOWER_BOUND = 100;
    private static final int INITIAL_BET_UPPER_BOUND = 10000;
    private static final int BETTING_UNIT = 100;

    private final BigDecimal value;

    private Money(final int value) {
        this.value = BigDecimal.valueOf(value);
    }

    public static Money initialBet(final int value) {
        validateRange(value);
        validateBetUnit(value);
        return new Money(value);
    }

    private static void validateRange(final int value) {
        if (isInvalidRange(value)) {
            throw new IllegalArgumentException(
                    "베팅 금액은 " + INITIAL_BET_LOWER_BOUND + " 이상, " + INITIAL_BET_UPPER_BOUND + " 이하여야 합니다."
            );
        }
    }

    private static boolean isInvalidRange(final int value) {
        return value < INITIAL_BET_LOWER_BOUND || INITIAL_BET_UPPER_BOUND < value;
    }

    private static void validateBetUnit(final int value) {
        if (isInvalidUnit(value)) {
            throw new IllegalArgumentException("베팅은 " + BETTING_UNIT + " 단위로 할 수 있습니다.");
        }
    }

    private static boolean isInvalidUnit(final int value) {
        return value % BETTING_UNIT != 0;
    }

    public Money calculatePrize(final Result result) {
        final BigDecimal prize = value.multiply(BigDecimal.valueOf(result.getRatio()));
        return new Money(prize.intValue());
    }

    public Money minus(final Money other) {
        final BigDecimal result = value.subtract(other.value);
        return new Money(result.intValue());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Money money = (Money) o;
        return Objects.equals(value, money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                '}';
    }

    public int getValue() {
        return value.intValue();
    }
}
