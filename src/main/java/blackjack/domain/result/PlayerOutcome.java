package blackjack.domain.result;

public enum PlayerOutcome {

    BLACKJACK_WIN(1.5),
    NORMAL_WIN(1),
    LOSE(-1),
    PUSH(0);

    private final double profitMultiplier;

    PlayerOutcome(final double profitMultiplier) {
        this.profitMultiplier = profitMultiplier;
    }

    public double getProfitMultiplier() {
        return profitMultiplier;
    }
}
