package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Referee {
    private static final int BUST_THRESHOLD = 21;

    public Result judge(Dealer dealer, List<Player> players) {
        Map<Player, MatchResult> gameResult = new LinkedHashMap<>();
        for (Player player : players) {
            gameResult.put(player, judgePlayer(player, dealer));
        }
        return new Result(dealer, gameResult);
    }

    private MatchResult judgePlayer(Player player, Dealer dealer) {
        if (player.isInitialBlackJack() && dealer.isInitialBlackJack()) {
            return MatchResult.DRAW;
        }
        if (player.isInitialBlackJack()) {
            return MatchResult.BLACKJACK;
        }
        return judgeByScore(player.getScore(), dealer.getScore());
    }

    private MatchResult judgeByScore(int playerScore, int dealerScore) {
        if (playerScore > BUST_THRESHOLD) {
            return MatchResult.LOSE;
        }
        if (dealerScore > BUST_THRESHOLD) {
            return MatchResult.WIN;
        }
        if (playerScore == dealerScore) {
            return MatchResult.DRAW;
        }
        if (playerScore > dealerScore) {
            return MatchResult.WIN;
        }
        return MatchResult.LOSE;
    }
}
