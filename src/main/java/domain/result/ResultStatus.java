package domain.result;

public enum ResultStatus {
    WIN(1),
    WIN_BLACKJACK(1.5),
    DRAW(0),
    LOSE(-1);
    
    private final double weight;
    
    ResultStatus(double weight) {
        this.weight = weight;
    }
    
    public static ResultStatus of(boolean isWin, boolean isDraw, boolean isBlackJack) {
        if (isWin && isBlackJack) {
            return WIN_BLACKJACK;
        }
        if (isWin) {
            return WIN;
        }
        if (isDraw) {
            return DRAW;
        }
        return LOSE;
    }
    
    public double getWeight() {
        return this.weight;
    }
}
