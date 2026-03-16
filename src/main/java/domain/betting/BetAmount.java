package domain.betting;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record BetAmount(long amount) {

    private static final int MINIMUM_BET_AMOUNT = 0;
    private static final int RATE_DIVISOR = 100;

    public static BetAmount from(long amount) {
        validatePositive(amount);
        return new BetAmount(amount);
    }

    private static void validatePositive(long amount) {
        if (amount < MINIMUM_BET_AMOUNT) {
            throw new IllegalArgumentException("배팅금액은 " + MINIMUM_BET_AMOUNT + " 이상이어야 합니다.");
        }
    }

    public long applyRate(int rate) {
        BigDecimal decimalRate = BigDecimal.valueOf(rate);
        BigDecimal decimalAmount = BigDecimal.valueOf(amount);
        BigDecimal divisor = BigDecimal.valueOf(RATE_DIVISOR);

        return decimalAmount.multiply(decimalRate)
                .divide(divisor, 0, RoundingMode.CEILING)
                .longValue();
    }

}
