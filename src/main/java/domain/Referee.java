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
        boolean playerBlackJack = player.isInitialBlackJack();
        boolean dealerBlackJack = dealer.isInitialBlackJack();

        if (playerBlackJack && dealerBlackJack) {
            return MatchResult.DRAW;
        }
        if (playerBlackJack) {
            return MatchResult.BLACKJACK;
        }

        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();

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
