package domain.blackJack;

public enum MatchResult {
    WIN(2),
    BLACKJACK(2.5),
    DRAW(1),
    LOSE(0);

    private final double rate;

    MatchResult(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
