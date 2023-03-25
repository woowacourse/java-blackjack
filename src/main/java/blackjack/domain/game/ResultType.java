package blackjack.domain.game;

public enum ResultType {
    BLACKJACK_WIN(1.5),
    WIN(1),
    LOSE(-1),
    TIE(0);

    private final double profitRateOfPlayer;

    ResultType(double profitRate) {
        profitRateOfPlayer = profitRate;
    }

    public double getProfitRateOfPlayer() {
        return profitRateOfPlayer;
    }
}
