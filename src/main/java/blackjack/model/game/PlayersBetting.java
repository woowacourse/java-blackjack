package blackjack.model.game;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import blackjack.model.BettingMoney;
import blackjack.model.player.Player;

public class PlayersBetting {

    private final Map<Player, BettingMoney> playersBettingMoney;

    public PlayersBetting() {
        this.playersBettingMoney = new HashMap<>();
    }

    public void depositBettingMoney(final Player player, final BettingMoney bettingMoney) {
        playersBettingMoney.put(player, bettingMoney);
    }

    public Map<Player, BettingMoney> getPlayersBettingMoney() {
        return Collections.unmodifiableMap(playersBettingMoney);
    }

    public BettingMoney withdrawMoney(final Player player) {
        if (playersBettingMoney.containsKey(player)) {
            return playersBettingMoney.get(player);
        }
        throw new IllegalArgumentException("존재하지 않는 플레이어입니다.");
    }

}
