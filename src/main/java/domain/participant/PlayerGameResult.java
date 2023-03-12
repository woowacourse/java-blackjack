package domain.participant;

public enum PlayerGameResult {
    WIN(1),
    DRAW(0),
    LOSE(-1),
    BLACKJACK(1.5);

    private final double benefitRatio;

    PlayerGameResult(final double benefitRatio) {
        this.benefitRatio = benefitRatio;
    }

    public int calculateBenefit(int betAmount) {
        return (int) (this.benefitRatio * betAmount);
    }
}
