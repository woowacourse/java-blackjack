package blackJack.domain.result;

import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class BettingOutcome {

    private final Map<Player, BettingAmount> bettingAmounts;

    public BettingOutcome(Map<Player, BettingAmount> bettingAmounts) {
        this.bettingAmounts = bettingAmounts;
    }

    public int getDealerProfit(Dealer dealer) {
        return getPlayersProfit(dealer).values().stream()
                .mapToInt(profit -> -profit)
                .sum();
    }

    public Map<Player, Integer> getPlayersProfit(Dealer dealer) {
        Map<Player, Integer> playersProfit = new LinkedHashMap<>();
        bettingAmounts.forEach(
                (key, value) -> playersProfit.put(key, value.calculateProfit(key.getMatchResult(dealer))));
        return playersProfit;
    }
}
