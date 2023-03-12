package betting;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import participants.Name;
import participants.Player;

public class PlayersBettingTable {
    private static final int NEGATIVE_UNIT = -1;
    private final Map<Player, BettingAmount> bettingMap = new LinkedHashMap<>();

    public void saveBet(Player player, BettingAmount bettingAmount) {
        bettingMap.put(player, bettingAmount);
    }

    public BettingAmount getBettingAmountByPlayer(Player player) {
        return bettingMap.get(player);
    }

    public Map<Name, BettingAmount> getBettingMap() {
        Map<Name, BettingAmount> nameBettingAmountMap = new LinkedHashMap<>();
        bettingMap.forEach((key,value)-> nameBettingAmountMap.put(key.getName(), value));
        return Collections.unmodifiableMap(nameBettingAmountMap);
    }

    public int calculateDealerReward() {
        return bettingMap.entrySet()
                .stream()
                .mapToInt(entry -> entry.getValue().calculateRewardByResult(entry.getKey().getResult()))
                .sum() * NEGATIVE_UNIT;
    }
}
