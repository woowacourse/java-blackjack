package blackjack.domain.result;

public enum Match {

    WIN_BLACKJACK(1.5),
    WIN( 1),
    LOSE_BLACKJACK(-1.5),
    LOSE( -1),
    DRAW( 0)
    ;

    private final double ratio;

    Match(double ratio) {
        this.ratio = ratio;
    }

    public double getRatio() {
        return ratio;
    }
}
