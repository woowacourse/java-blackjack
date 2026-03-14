package team.blackjack.domain;

import java.math.BigDecimal;

public final class Bet {
    private static final BigDecimal BLACKJACK_PAYOUT_RATIO = new BigDecimal("1.5");
    private static final BigDecimal WIN_PAYOUT_RATIO = BigDecimal.ONE;
    private static final BigDecimal DRAW_PAYOUT_RATIO = BigDecimal.ZERO;
    private static final BigDecimal LOSE_PAYOUT_RATIO = new BigDecimal("-1.0");

    private final BigDecimal stake;

    private Bet(BigDecimal stake) {
        this.stake = stake;
    }

    public static Bet from(int stake) {
        return new Bet(BigDecimal.valueOf(stake));
    }

    public BigDecimal calculatePayout(Result result) {
        return stake.multiply(payoutRatio(result));
    }

    private BigDecimal payoutRatio(Result result) {
        if (result == Result.BLACKJACK) {
            return BLACKJACK_PAYOUT_RATIO;
        }
        if (result == Result.WIN) {
            return WIN_PAYOUT_RATIO;
        }
        if (result == Result.LOSE) {
            return LOSE_PAYOUT_RATIO;
        }
        return DRAW_PAYOUT_RATIO;
    }
}
