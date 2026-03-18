package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Referee {

    public Result judge(Dealer dealer, List<Player> players) {
        Map<Player, MatchResult> gameResult = new LinkedHashMap<>();
        for (Player player : players) {
            gameResult.put(player, judgePlayer(player, dealer));
        }
        return new Result(dealer, gameResult);
    }

    private MatchResult judgePlayer(Player player, Dealer dealer) {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return MatchResult.DRAW;
        }
        if (dealer.isBlackJack()) {
            return MatchResult.LOSE;
        }
        if (player.isBlackJack()) {
            return MatchResult.BLACKJACK;
        }
        return judgeByScore(player, dealer);
    }

    private MatchResult judgeByScore(Player player, Dealer dealer) {
        if (player.isBust()) {
            return MatchResult.LOSE;
        }
        if (dealer.isBust()) {
            return MatchResult.WIN;
        }
        if (player.getScore() == dealer.getScore()) {
            return MatchResult.DRAW;
        }
        if (player.getScore() > dealer.getScore()) {
            return MatchResult.WIN;
        }
        return MatchResult.LOSE;
    }
}
