package blackjack.domain;

import blackjack.domain.participant.Player;

import java.util.HashMap;
import java.util.Map;

public class Betting {
    private final Map<Player, Integer> bettingTable = new HashMap<>();

    public void put(Player player, Integer money) {
        bettingTable.put(player, money);
    }

    public Integer get(Player player) {
        return bettingTable.get(player);
    }
}
