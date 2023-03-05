package blackjack.domain.participant.dealer;

import blackjack.domain.game.WinningResult;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class DealerWinningResult {
    private final EnumMap<WinningResult, Integer> resultToCount = new EnumMap<>(WinningResult.class) {
        {
            put(WinningResult.WIN, 0);
            put(WinningResult.TIE, 0);
            put(WinningResult.LOSE, 0);
        }
    };

    public void addWin() {
        resultToCount.put(WinningResult.WIN, resultToCount.get(WinningResult.WIN) + 1);
    }

    public void addTie() {
        resultToCount.put(WinningResult.TIE, resultToCount.get(WinningResult.TIE) + 1);
    }

    public void addLose() {
        resultToCount.put(WinningResult.LOSE, resultToCount.get(WinningResult.LOSE) + 1);
    }

    public Map<WinningResult, Integer> getResultToCount() {
        Map<WinningResult, Integer> newMap = new HashMap<>(resultToCount);
        return Map.copyOf(newMap);
    }
}
