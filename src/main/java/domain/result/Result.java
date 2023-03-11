package domain.result;

import java.math.BigDecimal;

public enum Result {
    WIN(new BigDecimal(1)),
    LOSE(new BigDecimal(-1)),
    TIE(new BigDecimal(1));

    private final BigDecimal prizeRatio;

    Result(BigDecimal prizeRatio) {
        this.prizeRatio = prizeRatio;
    }

    public static Result isHigherPlayerHandValue(int playerHandValue, int dealerHandValue) {
        if (playerHandValue > dealerHandValue) {
            return WIN;
        }
        return LOSE;
    }

    public static Result isGreaterPlayerHandCount(int playerHandCount, int dealerHandCount) {
        if (playerHandCount == dealerHandCount) {
            return TIE;
        }
        if (playerHandCount > dealerHandCount) {
            return LOSE;
        }
        return WIN;
    }

    public BigDecimal getPrizeRatio() {
        return prizeRatio;
    }
}
