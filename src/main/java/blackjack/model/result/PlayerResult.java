package blackjack.model.result;

public enum PlayerResult {

    WIN,
    DRAW,
    LOSE;

    public double getProfitMultiple(boolean isBlackjack) {
        if (this == WIN && isBlackjack) {
            return 1.5;
        }

        if (this == WIN) {
            return 1;
        }

        if (this == DRAW) {
            return 0;
        }

        return -1;
    }
}
