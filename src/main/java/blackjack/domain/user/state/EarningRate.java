package blackjack.domain.user.state;

public enum EarningRate {

    BLACKJACK_WIN(1.5),
    WIN(1.0),
    LOSS(-1.0),
    TIE(0.0),
    ;

    private final double rate;

    EarningRate(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
