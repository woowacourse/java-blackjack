package blackjack.domain.bet;

public enum BetRate {

    BLAKJACK(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private double rate;

    BetRate(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
