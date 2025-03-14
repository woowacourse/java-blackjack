package blackjack.domain;

import java.util.HashMap;
import java.util.Map;

import blackjack.domain.gamer.Player;

public class GameRound {

    private final Map<Player, Integer> bettingRecord = new HashMap<>();

    public void betting(Player player, int money) {
        bettingRecord.put(player, money);
    }
}
