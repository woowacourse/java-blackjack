package blackjack.domain.player;

public enum Result {
    BLACKJACK(1.5),
    WIN(1),
    PUSH(0),
    LOSE(-1);

    private final double payoutRatio;

    Result(final double payoutRatio) {
        this.payoutRatio = payoutRatio;
    }
}
