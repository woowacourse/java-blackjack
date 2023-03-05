package domain.result;

import java.util.HashMap;
import java.util.Map;

public class ResultCount {

    private Map<GameResult, Integer> resultCounts = new HashMap<>();

    public ResultCount() {
        initResults(resultCounts);
    }

    private void initResults(Map<GameResult, Integer> resultCounts) {
        resultCounts.put(GameResult.WIN, 0);
        resultCounts.put(GameResult.TIE, 0);
        resultCounts.put(GameResult.LOSE, 0);
    }

    public void addWinCount() {
        resultCounts.put(GameResult.WIN, resultCounts.get(GameResult.WIN) + 1);
    }

    public void addTieCount() {
        resultCounts.put(GameResult.TIE, resultCounts.get(GameResult.TIE) + 1);
    }

    public void addLoseCount() {
        resultCounts.put(GameResult.LOSE, resultCounts.get(GameResult.LOSE) + 1);
    }

    public Integer findWinCount() {
        return resultCounts.get(GameResult.WIN);
    }

    public Integer findTieCount() {
        return resultCounts.get(GameResult.TIE);
    }

    public Integer findLoseCount() {
        return resultCounts.get(GameResult.LOSE);
    }
}
