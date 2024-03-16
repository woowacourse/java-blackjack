package model.participant.state;

public enum MatchState {
    PLAYING(bettingAmount -> 0),
    TURNOVER(bettingAmount -> 0),
    WIN(bettingAmount -> bettingAmount),
    DRAW(bettingAmount -> 0),
    LOSE(bettingAmount -> -1 * bettingAmount),
    BLACK_JACK(bettingAmount -> (int) (1.5 * bettingAmount));

    private final EarningCalculator earningCalculator;

    MatchState(EarningCalculator earningCalculator) {
        this.earningCalculator = earningCalculator;
    }

    public long calculateEarnings(long bettingAmount) {
        return earningCalculator.calculate(bettingAmount);
    }
}
