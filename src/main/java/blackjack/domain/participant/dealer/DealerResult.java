package blackjack.domain.participant.dealer;

import blackjack.domain.game.Result;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class DealerResult {
    private final EnumMap<Result, Integer> resultToCount = new EnumMap<>(Result.class) {
        {
            put(Result.WIN, 0);
            put(Result.TIE, 0);
            put(Result.LOSE, 0);
        }
    };

    public void addWin() {
        resultToCount.put(Result.WIN, resultToCount.get(Result.WIN) + 1);
    }

    public void addTie() {
        resultToCount.put(Result.TIE, resultToCount.get(Result.TIE) + 1);
    }

    public void addLose() {
        resultToCount.put(Result.LOSE, resultToCount.get(Result.LOSE) + 1);
    }

    public Map<Result, Integer> getResultToCount() {
        Map<Result, Integer> newMap = new HashMap<>(resultToCount);
        return Map.copyOf(newMap);
    }
}
