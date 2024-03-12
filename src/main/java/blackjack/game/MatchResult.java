package blackjack.game;

public enum MatchResult {

    LOSE(-1),
    NORMAL_WIN(1),
    BLACKJACK_WIN(1.5),
    TIE(0),
    ;

    private final double profitRate;

    MatchResult(double profitRate) {
        this.profitRate = profitRate;
    }

    public int calculateProfit(int betMoney) {
        return (int) (betMoney * profitRate);
    }
}
