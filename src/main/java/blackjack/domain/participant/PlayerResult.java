package blackjack.domain.participant;

import blackjack.domain.Result;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerResult {

    private final Map<Player, Result> playerResult;

    public PlayerResult() {
        this.playerResult = new LinkedHashMap<>();
    }

    public void calculatePlayerResult(Player player, Dealer dealer) {
        if (player.isBlackJack()) {
            playerResult.put(player, judgeResultBlackJackPlayer(dealer));
            return;
        }
        if (player.isBust()) {
            playerResult.put(player, judgeResultBustPlayer());
            return;
        }
        playerResult.put(player, judgeResultStayPlayer(player, dealer));
    }

    private Result judgeResultBlackJackPlayer(Dealer dealer) {
        if (dealer.isBlackJack()) {
            return Result.PUSH;
        }
        return Result.BLACKJACK;
    }

    private Result judgeResultBustPlayer() {
        return Result.LOSE;
    }

    private Result judgeResultStayPlayer(Player player, Dealer dealer) {
        if (dealer.isBust() || player.calculateSumOfRank() > dealer.calculateSumOfRank()) {
            return Result.WIN;
        }
        if (dealer.isBlackJack() || player.calculateSumOfRank() < dealer.calculateSumOfRank()) {
            return Result.LOSE;
        }
        return Result.PUSH;
    }

    public Result getPlayerResult(final Player player) {
        return playerResult.get(player);
    }

    public Map<Player, Result> getPlayerResults() {
        return this.playerResult;
    }
}
