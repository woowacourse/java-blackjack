package blackjack.model.result;

public enum ResultCommand {
    WIN(1),
    BLACK_JACK_WIN(1.5),
    LOSE(-1),
    DRAW(1),
    ;

    private final double rate;

    ResultCommand(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
