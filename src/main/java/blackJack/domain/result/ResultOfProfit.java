package blackJack.domain.result;

import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ResultOfProfit {

    private final Map<Player, BettingAmount> bettingAmounts;

    public ResultOfProfit(Map<Player, String> inputs) {
        bettingAmounts = new LinkedHashMap<>();
        for (Entry<Player, String> entry : inputs.entrySet()) {
            bettingAmounts.put(entry.getKey(), BettingAmount.newInstanceByString(entry.getValue()));
        }
    }

    public int getDealerProfit(Dealer dealer) {
        return getPlayersProfit(dealer).values().stream()
                .mapToInt(profit -> -profit)
                .sum();
    }

    public Map<Player, Integer> getPlayersProfit(Dealer dealer) {
        Map<Player, Integer> playersProfit = new LinkedHashMap<>();
        for (Entry<Player, BettingAmount> entry : bettingAmounts.entrySet()) {
            MatchResult playerMatchResult = entry.getKey().getMatchResult(dealer);
            playersProfit.put(entry.getKey(), entry.getValue().calculateProfit(playerMatchResult));
        }
        return playersProfit;
    }
}
