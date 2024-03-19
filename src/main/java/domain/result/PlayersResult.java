package domain.result;

import domain.gamer.Player;

import java.util.Collections;
import java.util.Map;

public class PlayersResult {

    private final Map<Player, Money> playerResult;

    public PlayersResult(Map<Player, Money> playerResult) {
        this.playerResult = playerResult;
    }

    public void applyProfitToPlayer(Player player, WinState winState) {
        playerResult.get(player).applyProfitRate(winState);
    }

    public int sumAllPlayerProfit() {
        return playerResult.values().stream()
                .mapToInt(Money::getMoney)
                .sum();
    }

    public Map<Player, Money> getPlayerResult() {
        return Collections.unmodifiableMap(playerResult);
    }
}
