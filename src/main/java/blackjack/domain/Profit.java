package blackjack.domain;

import blackjack.domain.participant.Result;

public class Profit {

    private final double baseMoney;

    private Profit(final double baseMoney) {
        this.baseMoney = baseMoney;
    }

    public static Profit of(final double baseMoney) {
        return new Profit(baseMoney);
    }

    public double calculateProfit(final Result result, final boolean isBlackJackPlayer) {
        if (result.equals(Result.WIN) && isBlackJackPlayer) {
            return this.baseMoney * 1.5;
        }
        if (result.equals(Result.LOSE)) {
            return -this.baseMoney;
        }
        return this.baseMoney;
    }
}
