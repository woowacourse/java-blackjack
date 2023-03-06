package player;

import static blackjackgame.Result.LOSE;
import static blackjackgame.Result.TIE;
import static blackjackgame.Result.WIN;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import blackjackgame.Result;

public class DealerResult {
    private final EnumMap<Result, Count> dealerResult = new EnumMap<>(Result.class) {
        {
            put(WIN, new Count());
            put(TIE, new Count());
            put(LOSE, new Count());
        }
    };

    public void addWin() {
        dealerResult.get(WIN).addCount();
    }

    public void addTie() {
        dealerResult.get(TIE).addCount();
    }

    public void addLose() {
        dealerResult.get(LOSE).addCount();
    }

    public Map<Result, Count> getDealerResult() {
        return Map.copyOf(new HashMap<>(dealerResult));
    }
}
