package blackjack.domain;

import java.util.HashMap;
import java.util.Map;

import blackjack.domain.gamer.Player;

public class GameRound {

    private final Map<Player, Integer> initialBettingMoney = new HashMap<>();
    private final Map<Player, Integer> finalBettingMoney = new HashMap<>();

    public void betting(Player player, int money) {
        initialBettingMoney.put(player, money);
    }

    public boolean loseIfBust(Player player) {
        if (player.isBust()) {
            finalBettingMoney.put(player, 0);
            return true;
        }
        return false;
    }

    public int getFinalBettingMoney(Player player) {
        return finalBettingMoney.get(player);
    }
}
