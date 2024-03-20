package model.participant.state;

public enum MatchState {
    PLAYING(bettingAmount -> unacceptedCalculation()),
    TURNOVER(bettingAmount -> unacceptedCalculation()),
    WIN(bettingAmount -> bettingAmount),
    DRAW(bettingAmount -> 0),
    LOSE(bettingAmount -> -1 * bettingAmount),
    BLACK_JACK(bettingAmount -> (long) (1.5 * bettingAmount));

    private final EarningCalculator earningCalculator;

    MatchState(EarningCalculator earningCalculator) {
        this.earningCalculator = earningCalculator;
    }

    private static long unacceptedCalculation() {
        throw new IllegalStateException("Calculation can not be executed before Turnover.");
    }

    public long calculateEarnings(long bettingAmount) {
        return earningCalculator.calculate(bettingAmount);
    }
}
