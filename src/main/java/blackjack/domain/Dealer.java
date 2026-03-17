package blackjack.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant{

    private static final int DEALER_HIT_LIMIT = 17;

    public Dealer() {
        super("딜러");
    }

    public boolean isDealerNotDone() {
        return calculateTotalScore() < DEALER_HIT_LIMIT;
    }


    public GameResult judgeResult(List<Participant> players) {
        Map<Participant, ScoreCompareResult> playerResults = new HashMap<>();
        for (Participant player : players) {
            ScoreCompareResult result = compareScore(player, this);
            playerResults.put(player, toPlayerResult(result));
        }

        return new GameResult(playerResults);
    }

    private ScoreCompareResult compareScore(Participant player, Participant dealer) {
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
