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
            if (player.isBlackJack() && result == MatchResultType.DRAW) {
                betResult.put(player, 0L);
                continue;
            }
            if (player.isBlackJack() && result == MatchResultType.WIN) {
                betResult.put(player, (long) (values.get(player).getValue() * 1.5));
                continue;
            }
            if (player.isBust()) {
                betResult.put(player, values.get(player).getValue() * -1L);
                continue;
            }
            if (result == MatchResultType.WIN) {
                betResult.put(player, values.get(player).getValue());
                continue;
            }
            if (result == MatchResultType.LOSE) {
                betResult.put(player, values.get(player).getValue() * -1L);
                continue;
            }
            if (result == MatchResultType.DRAW) {
                betResult.put(player, 0L);
            }
        }
        return betResult;
    }

    public long calculateDealerBetResult(Map<Player, Long> betResult) {
        return -1L * betResult.values().stream()
                .mapToLong(Long::longValue)
                .sum();
    }
}
