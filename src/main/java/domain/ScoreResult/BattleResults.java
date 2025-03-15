package domain.ScoreResult;

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

    public int requestWinCounts() {
        if (battleResults.containsKey(BattleResult.WIN)) {
            return battleResults.get(BattleResult.WIN);
        }

        return 0;
    }

    public int requestDrawCounts() {
        if (battleResults.containsKey(BattleResult.DRAW)) {
            return battleResults.get(BattleResult.DRAW);
        }

        return 0;
    }

    public int requestLoseCounts() {
        if (battleResults.containsKey(BattleResult.LOSE)) {
            return battleResults.get(BattleResult.LOSE);
        }

        return 0;
    }
}
