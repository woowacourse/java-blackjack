package domain.game;

public enum PlayerResult {
    BLACKJACK_WIN(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1),
    ;

    private final double multiple;

    PlayerResult(double multiple) {
        this.multiple = multiple;
    }

    public int getProfit(int betAmount) {
        return (int) (betAmount * this.multiple);
    }
}
