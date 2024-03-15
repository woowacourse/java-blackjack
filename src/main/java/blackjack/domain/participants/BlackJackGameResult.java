package blackjack.domain.participants;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGameResult {

    private final Map<Player, Result> gameResult;

    public BlackJackGameResult(List<Player> players, Dealer dealer) {
        this.gameResult = new LinkedHashMap<>();
        players.forEach(player -> gameResult.put(player, calculateState(player, dealer)));
    }

    private Result calculateState(Player player, Dealer dealer) {
        if (isWin(player, dealer)) {
            return Result.WIN;
        }
        if (isTie(player, dealer)) {
            return Result.TIE;
        }
        return Result.LOSE;
    }

    private boolean isWin(Player player, Dealer dealer) {
        if (player.isBust()) {
            return false;
        }
        if (dealer.isBust()) {
            return true;
        }
        return player.calculateScore() > dealer.calculateScore();
    }

    private boolean isTie(Player player, Dealer dealer) {
        if (player.isBust() || dealer.isBust()) {
            return false;
        }
        return player.isBlackjack() && dealer.isBlackjack();
    }

    public Result getState(Player player) {
        return gameResult.get(player);
    }

    public Map<Player, Result> getGameResult() {
        return new HashMap<>(gameResult);
    }
}
