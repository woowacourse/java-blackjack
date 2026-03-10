package blackjack.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Referee {

    // 승패 판정 로직
    public GameResult judgeResult(List<Player> players, Dealer dealer) {
        Map<ScoreCompareResult, Integer> dealerResult = new HashMap<>();
        Map<Player, ScoreCompareResult> playerResults = new HashMap<>();

        for (Player player : players) {
            ScoreCompareResult result = compareScore(player, dealer);
            playerResults.put(player, toPlayerResult(result));
            dealerResult.merge(toDealerKey(result), 1, Integer::sum);
        }

        return new GameResult(dealerResult, playerResults);
    }

    public ScoreCompareResult compareScore(Player player, Dealer dealer) {
        boolean isPlayerBust = player.isBust();
        boolean isDealerBust = dealer.isBust();

        if (isPlayerBust || isDealerBust) {
            return compareScoreWhenBust(isPlayerBust);
        }

        return compareScoreWhenNotBust(player.calculateTotalScore(), dealer.calculateTotalScore());
    }

    private ScoreCompareResult toPlayerResult(ScoreCompareResult result) {
        if (result == ScoreCompareResult.DEALER_WIN) {
            return ScoreCompareResult.PLAYER_LOSS;
        }
        return result;
    }

    private ScoreCompareResult toDealerKey(ScoreCompareResult result) {
        if (result == ScoreCompareResult.PLAYER_WIN) {
            return ScoreCompareResult.DEALER_LOSS;
        }
        return result;
    }

    private ScoreCompareResult compareScoreWhenBust(boolean isPlayerBust) {
        if (isPlayerBust) {
            return ScoreCompareResult.DEALER_WIN;
        }
        return ScoreCompareResult.PLAYER_WIN;
    }

    private ScoreCompareResult compareScoreWhenNotBust(int playerTotalScore, int dealerTotalScore) {
        if (playerTotalScore > dealerTotalScore) {
            return ScoreCompareResult.PLAYER_WIN;
        }
        if (playerTotalScore < dealerTotalScore) {
            return ScoreCompareResult.DEALER_WIN;
        }
        return ScoreCompareResult.PUSH;
    }
}
