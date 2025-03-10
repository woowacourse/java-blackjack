package domain.ScordResult;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class BattleResults {
    private final Map<BattleResult, Integer> battleResults;

    public BattleResults() {
        this.battleResults = new EnumMap<>(BattleResult.class);
    }

    public void addResult(BattleResult result) {
        battleResults.put(result, battleResults.getOrDefault(result, 0) + 1);
    }

    public Map<BattleResult, Integer> getResults() {
        return Collections.unmodifiableMap(battleResults);
    }
}
