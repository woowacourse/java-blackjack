package blackjack.domain.participant;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerResult {

    private final Map<Player, Result> playerResult;

    public PlayerResult() {
        this.playerResult = new LinkedHashMap<>();
    }

    public void calculatePlayerResult(Player player, Dealer dealer) {
        if (player.isBlackJack()) {
            playerResult.put(player, judgeResultWhenBlackJackPlayer(dealer));
            return;
        }
        if (player.isBust()) {
            playerResult.put(player, judgeResultWhenBustPlayer());
            return;
        }
        playerResult.put(player, judgeResultWhenNotBustPlayer(player, dealer));
    }

    private Result judgeResultWhenBlackJackPlayer(Dealer dealer) {
        if (dealer.isBlackJack()) {
            return Result.PUSH;
        }
        return Result.WIN;
    }

    private Result judgeResultWhenBustPlayer() {
        return Result.LOSE;
    }

    private Result judgeResultWhenNotBustPlayer(Player player, Dealer dealer) {
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
