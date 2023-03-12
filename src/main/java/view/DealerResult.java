package view;

import domain.GameResult;
import java.util.Map;

public class DealerResult {

    private final Map<GameResult, Integer> result;

    public DealerResult(Map<GameResult, Integer> results) {
        result = results;
    }

    public int getWin() {
        return result.getOrDefault(GameResult.WIN, 0);
    }

    public int getLose() {
        return result.getOrDefault(GameResult.LOSE, 0);
    }

    public int getDraw() {
        return result.getOrDefault(GameResult.DRAW, 0);
    }
}
