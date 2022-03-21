package blackjack.domain;

import blackjack.domain.player.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class BettingBox {
    private final Map<Player, BettingMoney> bettingBox;

    public BettingBox(Map<Player, BettingMoney> bettingBox) {
        this.bettingBox = new LinkedHashMap<>(bettingBox);
    }

    public BettingMoney findBettingMoney(Player player) {
        return bettingBox.get(player);
    }
}
