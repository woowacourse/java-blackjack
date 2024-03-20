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
        throw new IllegalStateException("승부가 정해지기 전에는 배팅 금액 계산이 불가능합니다.");
    }

    public long calculateEarnings(long bettingAmount) {
        return earningCalculator.calculate(bettingAmount);
    }
}
