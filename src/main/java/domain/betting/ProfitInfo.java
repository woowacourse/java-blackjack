package domain.betting;

import domain.participant.Player;
import java.util.HashMap;
import java.util.Map;

public class ProfitInfo {

    private final Map<Player, Money> profitMap;

    private ProfitInfo(Map<Player, Money> profitMap) {
        this.profitMap = profitMap;
    }

    public static ProfitInfo withNoEntry() {
        return new ProfitInfo(new HashMap<>());
    }

    public void add(Player player, Money profit) {
        profitMap.put(player, profit);
    }

    public Money findProfitBy(Player player) {
        return profitMap.get(player);
    }
}
