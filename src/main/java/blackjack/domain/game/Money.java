package blackjack.domain.game;

import blackjack.domain.player.Result;
import java.math.BigDecimal;
import java.util.Objects;

public final class Money {
    public static final Money ZERO = new Money(0);
    private static final int INITIAL_BET_LOWER_BOUND = 100;
    static final String INVALID_INITIAL_BET_VALUE = "초기값은 " + INITIAL_BET_LOWER_BOUND + "이상이어야 합니다.";

    private final BigDecimal value;

    private Money(final int value) {
        this.value = BigDecimal.valueOf(value);
    }

    public static Money initialBet(final int value) {
        validate(value);
        return new Money(value);
    }

    private static void validate(final int value) {
        if (value < INITIAL_BET_LOWER_BOUND) {
            throw new IllegalArgumentException(INVALID_INITIAL_BET_VALUE);
        }
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

    public int getValue() {
        return value.intValue();
    }
}
