package blackjack.domain;

import java.util.HashMap;
import java.util.Map;

public class BetManager {

    private final Map<Player, Integer> betManager;

    public BetManager() {
        betManager = new HashMap<>();
    }

    public void add(Player player, int betMoney) {
        betManager.put(player, betMoney);
    }

    public Map<Player, Integer> getBetManager() {
        return betManager;
    }
}
