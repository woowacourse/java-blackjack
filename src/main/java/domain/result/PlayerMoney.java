package domain.result;

import domain.player.Player;

import java.util.Collections;
import java.util.Map;

public class PlayerMoney {

    private final Map<Player, Money> playerMoney;

    public PlayerMoney(Map<Player, Money> playerMoney) {
        this.playerMoney = playerMoney;
    }

    public void applyProfitToPlayer(Player player, WinState winState) {
        playerMoney.get(player).applyProfitRate(winState);
    }

    public int sumAllPlayerMoney() {
        return playerMoney.values().stream()
                .mapToInt(Money::getMoney)
                .sum();
    }

    public Map<Player, Money> getPlayerMoney() {
        return Collections.unmodifiableMap(playerMoney);
    }
}
