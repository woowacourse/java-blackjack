package domain.participant;

import domain.ExceptionCode;

import java.math.BigDecimal;

public class BetAmount {

    private final BigDecimal betAmount;
    public BetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
        if (betAmount.compareTo(new BigDecimal(1000)) < 0) {
            throw new IllegalArgumentException(ExceptionCode.LEAK_BET_AMOUNT.getExceptionCode());
        }
    }

    public int calculatePrize(BigDecimal width) {
        return betAmount.multiply(width).toBigInteger().intValueExact();
    }
}
