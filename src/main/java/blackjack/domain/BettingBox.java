package blackjack.domain;

import blackjack.domain.player.Player;
import java.util.HashMap;
import java.util.Map;

public class BettingBox {
    private final Map<Player, BettingMoney> bettingBox;

    public BettingBox() {
        this.bettingBox = new HashMap<>();
    }

    public void betGuest(Player guest, BettingMoney bettingMoney) {
        bettingBox.put(guest, bettingMoney);
    }

    public BettingMoney findBettingMoney(Player player) {
        return bettingBox.get(player);
    }
}
