package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Referee {
    private static final int BUST_THRESHOLD = 21;

    public Result judge(Dealer dealer, List<Player> players) {
        Map<String, MatchResult> gameResult = new HashMap<>();
        for (Player player : players) {
            gameResult.put(player.getName(), isPlayerWin(player.getScore(), dealer.getScore()));
        }
        return new Result(dealer, gameResult);
    }

    private MatchResult isPlayerWin(int playerScore, int dealerScore) {
        if (playerScore > BUST_THRESHOLD) {
            return MatchResult.LOSE;
        }
        if (dealerScore > BUST_THRESHOLD) {
            return MatchResult.WIN;
        }

        if (dealerScore == playerScore) {
            return MatchResult.DRAW;
        }

        if (dealerScore > playerScore) {
            return MatchResult.LOSE;
        }

        return MatchResult.WIN;
    }
}
