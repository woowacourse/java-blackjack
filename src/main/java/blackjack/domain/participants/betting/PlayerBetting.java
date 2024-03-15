package blackjack.domain.participants.betting;

import blackjack.domain.participants.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerBetting {
    private final Map<Player, Profit> playerBetting;

    public PlayerBetting() {
        this.playerBetting = new LinkedHashMap<>();
    }

    public void bet(Player player, Profit profit) {
        playerBetting.put(player, profit);
    }

    public Profit getBettingProfit(Player player) {
        return playerBetting.get(player);
    }
}
