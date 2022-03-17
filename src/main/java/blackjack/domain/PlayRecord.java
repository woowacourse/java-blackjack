package blackjack.domain;

import static blackjack.domain.PlayStatus.*;

public enum PlayRecord {

    WIN,
    PUSH,
    LOSS,
    BLACKJACK;

    public static PlayRecord of(int dealerScore, int score, boolean isBlackjack) {
        if (isPlayerLoss(dealerScore, score)) {
            return LOSS;
        }

        if (dealerScore == score) {
            return PUSH;
        }

        if (isBlackjack) {
            return BLACKJACK;
        }

        return WIN;
    }

    private static boolean isPlayerLoss(int dealerScore, int score) {
        return isBust(score) || (!isBust(dealerScore) && score < dealerScore);
    }

    public PlayRecord getOpposite() {
        if (this == LOSS) {
            return WIN;
        }

        if (this == WIN) {
            return LOSS;
        }

        return PUSH;
    }
}
