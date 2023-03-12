package blackjack.domain.game;

public enum WinTieLose {
    WIN(1),
    TIE(0),
    LOSE(-1),
    BLACKJACK(1.5);
    private final double rate;

    WinTieLose(double rate) {
        this.rate = rate;
    }

    public double rate() {
        return this.rate;
    }
}
