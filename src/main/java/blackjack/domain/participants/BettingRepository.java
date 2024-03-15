package blackjack.domain.participants;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingRepository {
    private final Map<Player, Profit> bettingResult;

    public BettingRepository() {
        this.bettingResult = new LinkedHashMap<>();
    }

    public void bet(Player player, Profit profit) {
        bettingResult.put(player, profit);
    }

    public Profit getBettingProfit(Player player) {
        return bettingResult.get(player);
    }
}
