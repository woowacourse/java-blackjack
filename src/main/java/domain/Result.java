package domain;

import java.util.*;

public class Result {
    private final Map<String, ResultInfo> gameResult;

    public Result() {
        this.gameResult = new HashMap<>();
    }

    public Map<String, ResultInfo> getGameResult() {
        return Collections.unmodifiableMap(gameResult);
    }

    public void setEntry(String name, ResultInfo info) {
        gameResult.put(name, info);
    }

    public List<Integer> dealerResult() {
        List<Integer> dealerScoreBoard = new ArrayList<>(List.of(0, 0, 0));
        for (Map.Entry<String, ResultInfo> entry : gameResult.entrySet()) {
            calculateDealerScoreBoard(entry, dealerScoreBoard);
        }
        return dealerScoreBoard;
    }

    private void calculateDealerScoreBoard(Map.Entry<String, ResultInfo> entry, List<Integer> dealerScoreBoard) {
        if (entry.getValue().equals(ResultInfo.WIN)) {
            dealerScoreBoard.set(2, dealerScoreBoard.get(2) + 1);
        }
        if (entry.getValue().equals(ResultInfo.DEFEAT)) {
            dealerScoreBoard.set(0, dealerScoreBoard.getFirst() + 1);
        }
        if (entry.getValue().equals(ResultInfo.DRAW)) {
            dealerScoreBoard.set(1, dealerScoreBoard.get(1) + 1);
        }
    }
}
