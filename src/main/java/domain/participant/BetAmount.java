package domain.participant;

import domain.ExceptionCode;

import java.math.BigDecimal;

public class BetAmount {

    private static final BigDecimal MIN_BETTING_AMOUNT = new BigDecimal(1000);
    private static final int LESS_THAN_MIN_BETTING_COUNT = 0;
    private final BigDecimal betAmount;
    public BetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
        if (betAmount.compareTo(MIN_BETTING_AMOUNT) < LESS_THAN_MIN_BETTING_COUNT) {
            throw new IllegalArgumentException(ExceptionCode.LEAK_BET_AMOUNT.getExceptionCode());
        }
    }

    public int calculatePrize(BigDecimal width) {
        return betAmount.multiply(width).toBigInteger().intValueExact();
    }
}
