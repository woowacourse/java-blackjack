package domain.game;

public enum ProfitRatio {
    BLACKJACK(1.5),
    WIN(1),
    LOSE(-1),
    TIE(0);

    private final double ratio;

    ProfitRatio(double ratio) {
        this.ratio = ratio;
    }

    public static double fetchProfitRatio(int handValue, int dealerHandValue) {
        if (handValue == -1) {
            return BLACKJACK.ratio;
        }
        if (handValue == 0) {
            return LOSE.ratio;
        }
        if (handValue - dealerHandValue > 0) {
            return WIN.ratio;
        }
        if (handValue - dealerHandValue < 0) {
            return LOSE.ratio;
        }
        return TIE.ratio;
    }
}
