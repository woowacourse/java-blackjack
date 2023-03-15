package blackjack.domain.blackjack;

public enum ResultType {
    BLACKJACK_WIN(1.5),
    WIN(1),
    TIE(0),
    LOSE(-1),
    BLACKJACK_LOSE(-1);

    private final double playerProfit;

    ResultType(final double playerProfit) {
        this.playerProfit = playerProfit;
    }

    public double getPlayerProfit() {
        return playerProfit;
    }
}
