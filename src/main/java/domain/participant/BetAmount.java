package domain.participant;

import java.math.BigDecimal;

public record BetAmount(BigDecimal amount) {
    private static final BigDecimal MIN_BET_AMOUNT = new BigDecimal("0.00");

    public BetAmount {
        validateAmount(amount);
    }

    private void validateAmount(BigDecimal amount) {
        if (amount.compareTo(MIN_BET_AMOUNT) <= 0) {
            throw new IllegalArgumentException();
        }
    }
}
