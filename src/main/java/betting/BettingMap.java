package betting;

import java.util.LinkedHashMap;
import java.util.Map;

import participants.Player;

public class BettingMap {
    public static final int NEGATIVE_UNIT = -1;
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

    public int calculateDealerReward() {
        return bettingMap.entrySet()
                .stream()
                .mapToInt(entry -> entry.getValue().calculateRewardByResult(entry.getKey().getResult()))
                .sum() * NEGATIVE_UNIT;
    }
}
