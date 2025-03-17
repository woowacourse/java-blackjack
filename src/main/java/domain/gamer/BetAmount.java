package domain.gamer;

import domain.profit.Outcome;
import domain.profit.Profit;

public class BetAmount {

    private static final long MAX_BET_AMOUNT = 5_000_000_000L;
    private static final int MIN_BET_AMOUNT = 10_000;
    private final long betAmount;

    private BetAmount(final long betAmount) {
        validateBetAmount(betAmount);
        this.betAmount = betAmount;
    }

    public static BetAmount of(final String betAmount) {
        try {
            return new BetAmount(Long.parseLong(betAmount));
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("베팅 금액은 숫자로만 작성해주세요.");
        }
    }

    private void validateBetAmount(final long betAmount) {
        if (betAmount > MAX_BET_AMOUNT || betAmount < MIN_BET_AMOUNT) {
            throw new IllegalArgumentException("배팅금액은 1만원 ~ 50억까지만 가능합니다.");
        }
    }

    public Double calculateProfit(final Outcome outcome) {
        final Profit profit = Profit.findByOutcome(outcome);
        return profit.calculate(betAmount);
    }
}
