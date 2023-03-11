package blackjack.domain;

import java.util.HashMap;
import java.util.Map;

public class BetManager {

    private final Map<Player, Money> betManager;

    public BetManager() {
        betManager = new HashMap<>();
    }

    public void add(Player player, Money betMoney) {
        betManager.put(player, betMoney);
    }

    public Money getBetMoney(Player player) {
        return betManager.get(player);
    }
}
