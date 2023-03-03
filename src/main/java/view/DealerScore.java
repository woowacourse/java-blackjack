package view;

import domain.GameResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DealerScore {

    private final Map<GameResult, Integer> result = new HashMap<>();

    public DealerScore(List<PlayerScore> results) {
        init(results);
    }

    public void init(List<PlayerScore> results) {
        for (PlayerScore playerScore : results) {
            GameResult reverseResult = playerScore.getGameResult().reverse();
            result.put(reverseResult, result.getOrDefault(reverseResult, 0) + 1);
        }
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
