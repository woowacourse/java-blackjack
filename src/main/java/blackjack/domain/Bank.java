package blackjack.domain;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    Map<Player, Money> bank = new HashMap<>();

    public void bet(Player player, Money money) {
        bank.put(player, money);
    }

    public Money getProfit(GameResult gameResult, Player player) {
        return gameResult.calculateProfit(player, bank.get(player));
    }
}
