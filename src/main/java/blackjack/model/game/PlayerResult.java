package blackjack.model.game;

public enum PlayerResult {
    WIN(1),
    LOSE(-1),
    DRAW(0),
    BLACKJACK(1.5),
    ;
    private final double allocationRatio;

    PlayerResult(double allocationRatio) {
        this.allocationRatio = allocationRatio;
    }

    public double calculatePayout(int betAmount) {
        return betAmount * allocationRatio;

    }
}
