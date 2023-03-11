package domain;

public enum Result {

    DRAW(0),
    WIN(1),
    LOSE(-1),
    WIN_BY_BLACKJACK(1.5);

    private final double times;

    Result(double times) {
        this.times = times;
    }

    public double getTimes() {
        return times;
    }

}
