package blackjack.domain.betting;

import blackjack.domain.participant.Player;

import java.util.HashMap;
import java.util.Map;

public class Betting {
    private final Map<Player, Money> bettingTable = new HashMap<>();

    public void put(Player player, Money money) {
        bettingTable.put(player, money);
    }

    public Money get(Player player) {
        return bettingTable.get(player);
    }
}
