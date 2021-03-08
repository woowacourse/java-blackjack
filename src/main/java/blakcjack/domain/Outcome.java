package blakcjack.domain;

import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Player;

public enum Outcome {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String value;

    Outcome(final String value) {
        this.value = value;
    }

    public static Outcome of(final Player player, final Dealer dealer) {
        if (hasAnyBust(player, dealer)) {
            return judgeOutcomeByBust(player);
        }
        return judgeOutcomeByScore(player.calculateScore(), dealer.calculateScore());
    }

    private static boolean hasAnyBust(final Player player, final Dealer dealer) {
        return player.isBust() || dealer.isBust();
    }

    private static Outcome judgeOutcomeByBust(final Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        return WIN;
    }

    private static Outcome judgeOutcomeByScore(final int playerScore, final int dealerScore) {
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore < dealerScore) {
            return LOSE;
        }
        return DRAW;
    }

    public String getValue() {
        return value;
    }
}
