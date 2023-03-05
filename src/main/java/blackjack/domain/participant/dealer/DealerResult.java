package blackjack.domain.participant.dealer;

import blackjack.domain.game.Result;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class DealerResult {
    private final EnumMap<Result, Integer> dealerResult = new EnumMap<>(Result.class) {
        {
            put(Result.WIN, 0);
            put(Result.TIE, 0);
            put(Result.LOSE, 0);
        }
    };

    public void addWin() {
        dealerResult.put(Result.WIN, dealerResult.get(Result.WIN) + 1);
    }

    public void addTie() {
        dealerResult.put(Result.TIE, dealerResult.get(Result.TIE) + 1);
    }

    public void addLose() {
        dealerResult.put(Result.LOSE, dealerResult.get(Result.LOSE) + 1);
    }

    public Map<Result, Integer> getDealerResult() {
        Map<Result, Integer> newMap = new HashMap<>(dealerResult);
        return Map.copyOf(newMap);
    }
}
