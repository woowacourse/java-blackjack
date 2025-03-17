package bet;

import participant.Player;
import result.MatchResultType;

import java.util.HashMap;
import java.util.Map;

public class ProfitStatus {

    private final Map<Player, Money> values;

    public ProfitStatus(Map<Player, Money> values) {
        this.values = values;
    }

    public Map<Player, Money> getValues() {
        return values;
    }

    public Map<Player, Long> calculateBetResult(Map<Player, MatchResultType> playerMatchResult) {
        return new HashMap<>();
    }
}
