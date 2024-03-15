package blackjack.domain.participants;

public enum Outcome {
    BLACKJACK_WIN(1.5F),
    WIN(1),
    TIE(0),
    LOSE(-1);

    private final float benefit;

    Outcome(float benefit) {
        this.benefit = benefit;
    }

    public float getBenefit() {
        return benefit;
    }
}
