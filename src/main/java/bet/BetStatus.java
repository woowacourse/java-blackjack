package bet;

import participant.Player;
import result.MatchResultType;

import java.util.HashMap;
import java.util.Map;

public class BetStatus {

    private final Map<Player, Money> values;

    public BetStatus(Map<Player, Money> values) {
        this.values = values;
    }

    public Map<Player, Money> getValues() {
        return values;
    }

    public Map<Player, Money> calculateBetResult(Map<Player, MatchResultType> playerMatchResult) {
        HashMap<Player, Money> betResult = new HashMap<>();
        for (Map.Entry<Player, MatchResultType> entry : playerMatchResult.entrySet()) {
            Player player = entry.getKey();
            MatchResultType result = entry.getValue();
            Money money = values.get(player);
            BettingRateType bettingRateType = BettingRateType.of(player, result);
            betResult.put(player, bettingRateType.calculateProfitMoney(money));
        }
        return betResult;
    }

    public Money calculateDealerBetResult(Map<Player, Money> betResult) {
        Money money = betResult.values().stream()
                .reduce(Money::plus)
                .orElseThrow(() -> new IllegalArgumentException(""));
        return money.times(-1);
    }
}
