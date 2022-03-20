package blackJack.domain.result;

public enum MatchResult {
    BLACK_JACK_WIN(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private final double profitCoefficient;

    MatchResult(double profitCoefficient) {
        this.profitCoefficient = profitCoefficient;
    }

    public int calculateProfit(int bettingAmount) {
        return (int) (bettingAmount * profitCoefficient);
    }
}
