package blackjack.domain.result;

import blackjack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum Result {
    WIN,
    DRAW,
    LOSE;

    private static final int BLACKJACK_SCORE = 21;

    public static Map<Player, Result> makeResult(final List<Player> players, final int dealerScore) {
        Map<Player, Result> results = new LinkedHashMap<>();

        for (Player player : players) {
            results.put(player, compare(player, dealerScore));
        }
        return results;
    }

    private static Result compare(final Player player, final int dealerScore) {
        if (dealerScore > BLACKJACK_SCORE) {
            return judgeToBust(player);
        }
        return judgeToNotBust(player.calculateTotalScore(), dealerScore);
    }

    private static Result judgeToBust(final Player player) {
        if (player.isBust()) {
            return DRAW;
        }
        return WIN;
    }

    private static Result judgeToNotBust(final int playerScore, int dealerScore) {
        if (playerScore < dealerScore) {
            return LOSE;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }
        return WIN;
    }
}
