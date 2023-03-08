package betting;

import java.util.LinkedHashMap;
import java.util.Map;

import participants.Player;

public class BettingMap {
    private final Map<Player, BettingAmount> bettingMap = new LinkedHashMap<>();


    public void saveBet(Player player, BettingAmount bettingAmount) {
        bettingMap.put(player, bettingAmount);
    }

    public BettingAmount getBettingAmountByPlayer(Player player) {
        return bettingMap.get(player);
    }

    public Map<Player, BettingAmount> getBettingMap() {
        return bettingMap;
    }
}
