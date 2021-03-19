package blakcjack.domain.money;

import blakcjack.domain.outcome.Outcome;

import java.util.Arrays;
import java.util.function.Predicate;

public enum EarningRate {
    BLACKJACK(Outcome.WIN, true, 1.5),
    WIN(Outcome.WIN, false, 1),
    DRAW(Outcome.DRAW, false, 0),
    LOSE(Outcome.LOSE, false, -1);

    private final Outcome outcome;
    private final boolean isBlackJack;
    private final double rate;

    EarningRate(final Outcome outcome, final boolean isBlackJack, final double rate) {
        this.outcome = outcome;
        this.isBlackJack = isBlackJack;
        this.rate = rate;
    }

    public static EarningRate of(final Outcome outcome, final boolean isBlackJack) {
        return Arrays.stream(values())
                .filter(matchedFilter(outcome, isBlackJack))
                .findAny()
                .orElseThrow(() -> new IllegalEarningRateException());
    }

    private static Predicate<EarningRate> matchedFilter(final Outcome outcome, final boolean isBlackJack) {
        final Predicate<EarningRate> outcomeFilter = earningRate -> earningRate.outcome.equals(outcome);
        final Predicate<EarningRate> blackJackFilter = earningRate -> earningRate.isBlackJack == isBlackJack;

        if (outcome.isWin()) {
            return outcomeFilter.and(blackJackFilter);
        }
        return outcomeFilter;
    }

    public double getRate() {
        return rate;
    }
}
