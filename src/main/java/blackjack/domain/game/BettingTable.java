package blackjack.domain.game;

import blackjack.domain.user.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class BettingTable {
    private final Map<Player, BettingMoney> bets;

    public BettingTable() {
        this.bets = new LinkedHashMap<>();
    }

    public void put(Player player, BettingMoney money) {
        bets.put(player, money);
    }

    public Map<Player, BettingMoney> getBets() {
        return Map.copyOf(bets);
    }
}
