package domain.vo;

public enum RoundResult {

    WIN(1),
    LOSE(-1),
    DRAW(0);

    private static final int BUST_CONDITION = 21;

    private final double profitRate;

    RoundResult(double profitRate) {
        this.profitRate = profitRate;
    }

    public static RoundResult judgeAgainst(int dealerScore, int playerScore) {
        if (playerScore > BUST_CONDITION) return RoundResult.LOSE;
        if (dealerScore > BUST_CONDITION) return RoundResult.WIN;
        if (playerScore > dealerScore) return RoundResult.WIN;
        if (playerScore < dealerScore) return RoundResult.LOSE;
        return RoundResult.DRAW;
    }

    public int calculateProfit(int bettingAmount) {
        return (int) (bettingAmount * profitRate);
    }
}
