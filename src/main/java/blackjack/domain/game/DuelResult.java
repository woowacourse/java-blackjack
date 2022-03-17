package blackjack.domain.game;

public enum DuelResult {

    BLACKJACK_WIN(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0);

    private final double bettingYield;

    DuelResult(double bettingYield) {
        this.bettingYield = bettingYield;
    }

    public static DuelResult from(final Score score, final Score targetScore) {
        int compareResult = score.compareTo(targetScore);

        if (compareResult > 0) {
            return DuelResult.WIN;
        }
        if (compareResult < 0) {
            return DuelResult.LOSE;
        }
        return DuelResult.DRAW;
    }

    public int getProfitOf(int bettingAmount) {
        return (int) (bettingAmount * this.bettingYield);
    }
}
