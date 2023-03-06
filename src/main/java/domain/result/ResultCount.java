package domain.result;

import java.util.HashMap;
import java.util.Map;

import util.Constants;

public class ResultCount {

    private static final String NOT_FIGHT_RESULT_ERROR_MESSAGE = Constants.ERROR_PREFIX + "승부가 나지 않았습니다.";
    private static final String DEALER_WIN_WORD = "승 ";
    private static final String DEALER_TIE_WORD = "무 ";
    private static final String DEALER_LOSE_WORD = "패";

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

    public String findPlayerGameResult() {
        GameResult findGameResult = resultCounts.keySet().stream()
                .filter(gameResult -> resultCounts.get(gameResult) > 0)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FIGHT_RESULT_ERROR_MESSAGE));

        return GameResult.findGameResultName(findGameResult);
    }

    public String findDealerGameResult() {
        Integer winCount = resultCounts.get(GameResult.WIN);
        Integer tieCount = resultCounts.get(GameResult.TIE);
        Integer loseCount = resultCounts.get(GameResult.LOSE);
        StringBuilder dealerGameResult = calculateGameResult(winCount, tieCount, loseCount);
        return dealerGameResult.toString();
    }

    private StringBuilder calculateGameResult(Integer winCount, Integer tieCount, Integer loseCount) {
        StringBuilder dealerGameResult = new StringBuilder();
        if (winCount != 0) {
            dealerGameResult.append(winCount + DEALER_WIN_WORD);
        }
        if (tieCount != 0) {
            dealerGameResult.append(tieCount + DEALER_TIE_WORD);
        }
        if (loseCount != 0) {
            dealerGameResult.append(loseCount + DEALER_LOSE_WORD);
        }
        return dealerGameResult;
    }
}
