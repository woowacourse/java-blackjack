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

    public static Map<Result, Integer> resultOfDealer(final Map<Player, Result> playerResults) {
        Map<Result, Integer> dealerResults = new LinkedHashMap<>() {{
            put(WIN, 0);
            put(DRAW, 0);
            put(LOSE, 0);
        }};

        for (Result playerResult : playerResults.values()) {
            compareToPlayerResult(dealerResults, playerResult);
        }
        return dealerResults;
    }

    private static void compareToPlayerResult(final Map<Result, Integer> dealerResult, final Result playerResult) {
        if (playerResult == WIN) {
            dealerResult.put(LOSE, dealerResult.get(LOSE) + 1);
            return;
        }
        if (playerResult == DRAW) {
            dealerResult.put(DRAW, dealerResult.get(DRAW) + 1);
            return;
        }
        dealerResult.put(WIN, dealerResult.get(WIN) + 1);
    }
}
