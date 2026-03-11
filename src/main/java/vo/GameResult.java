package vo;

public enum GameResult {
    BLACKJACK(1.5),
    WIN(1.0),
    LOSE(-1.0),
    PUSH(0.0),
    BUST(-1.0);

    private final Double multiplier;

    GameResult(Double multiplier) {
        this.multiplier = multiplier;
    }

    public Long calculateProfit(Long betAmount) {
        return (long)(betAmount * multiplier);
    }
}
