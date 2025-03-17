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

    public Map<Player, Long> calculateBetResult(Map<Player, MatchResultType> playerMatchResult) {
        HashMap<Player, Long> betResult = new HashMap<>();
        for (Map.Entry<Player, MatchResultType> entry : playerMatchResult.entrySet()) {
            Player player = entry.getKey();
            MatchResultType result = entry.getValue();
            long betMoney = values.get(player).getValue();
            BettingRateType bettingRateType = BettingRateType.of(player, result);
            betResult.put(player, bettingRateType.calculateProfitMoney(betMoney));
        }
        return betResult;
    }

    public long calculateDealerBetResult(Map<Player, Long> betResult) {
        return -1 * betResult.values().stream()
                .mapToLong(Long::longValue)
                .sum();
    }
}
