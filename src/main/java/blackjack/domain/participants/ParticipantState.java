package blackjack.domain.participants;

public enum ParticipantState {
    BLACKJACK(1.5d), HIT(1.0d), STAY(1.0d), BUST(1.0d);

    private final double profitMultiplier;

    ParticipantState(double profitMultiplier) {
        this.profitMultiplier = profitMultiplier;
    }

    public double getProfitMultiplier() {
        return profitMultiplier;
    }
}
