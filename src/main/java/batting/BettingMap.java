package batting;

import java.util.LinkedHashMap;
import java.util.Map;

import participants.Player;

public class BettingMap {
    private final Map<Player, Amount> bettingMap = new LinkedHashMap<>();


    public void saveBet(Player player, Amount amount) {
        bettingMap.put(player, amount);
    }

    public Amount getBettingAmountByPlayer(Player player) {
        return bettingMap.get(player);
    }
}
