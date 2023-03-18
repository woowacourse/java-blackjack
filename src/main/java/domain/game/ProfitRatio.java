package domain.game;

import domain.Number;

public enum ProfitRatio {
    BLACKJACK(1.5),
    WIN(1),
    LOSE(-1),
    TIE(0);

    private final double ratio;

    ProfitRatio(final double ratio) {
        this.ratio = ratio;
    }

    public static double fetchProfitRatio(final int handValue, final int dealerHandValue) {
        if (handValue == Number.BLACKJACK_RESULT_VALUE.get()) {
            return BLACKJACK.ratio;
        }
        if (handValue == Number.BUST_VALUE.get()) {
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
