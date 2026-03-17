package blackjack.domain.game;

public enum GameResult {
    PLAYER_BLACKJACK(3, 2),
    PLAYER_WIN(1, 1),
    DEALER_WIN(-1, 1),
    PUSH(0, 1);

    private final long numerator;
    private final long denominator;

    GameResult(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public long calculateProfit(long betAmount) {
        return betAmount * this.numerator / this.denominator;
    }
}
