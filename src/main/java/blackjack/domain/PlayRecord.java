package blackjack.domain;

import static blackjack.domain.PlayStatus.*;

public enum PlayRecord {

    WIN("승"),
    PUSH("무"),
    LOSS("패"),
    BLACKJACK("블랙잭");

    private final String name;

    PlayRecord(String name) {
        this.name = name;
    }

    public static PlayRecord of(int dealerScore, int score) {
        if (isPlayerLoss(dealerScore, score)) {
            return LOSS;
        }

        if (dealerScore == score) {
            return PUSH;
        }

        if (PlayStatus.isBlackjack(score)) {
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

    public String getName() {
        return name;
    }
}
