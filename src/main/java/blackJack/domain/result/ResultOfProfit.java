package blackJack.domain.result;

import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResultOfProfit {

    private final Map<Player, BettingAmount> bettingAmounts;

    public ResultOfProfit(Map<Player, String> inputs) {
        bettingAmounts = new LinkedHashMap<>();
        inputs.forEach((key, value) -> bettingAmounts.put(key, BettingAmount.newInstanceByString(value)));
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
