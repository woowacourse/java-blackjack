package blackjack.domain.participants;

public enum Result {
    BLACKJACK_WIN(1.5F),
    WIN(1),
    TIE(0),
    LOSE(-1);

    private final float benefit;

    Result(float benefit) {
        this.benefit = benefit;
    }

    public float getBenefit() {
        return benefit;
    }
}
