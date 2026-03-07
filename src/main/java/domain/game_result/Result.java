package domain.game_result;

import java.util.*;

public class Result {
    private static final int INIT_COUNT = 0;
    private static final int WIN_INDEX = 0;
    private static final int DRAW_INDEX = 1;
    private static final int DEFEAT_INDEX = 2;
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
        List<Integer> dealerScoreBoard = new ArrayList<>(List.of(INIT_COUNT, INIT_COUNT, INIT_COUNT));
        for (Map.Entry<String, ResultInfo> entry : gameResult.entrySet()) {
            calculateDealerScoreBoard(entry, dealerScoreBoard);
        }
        return dealerScoreBoard;
    }

    private void calculateDealerScoreBoard(Map.Entry<String, ResultInfo> entry, List<Integer> dealerScoreBoard) {
        if (entry.getValue().equals(ResultInfo.WIN)) {
            dealerScoreBoard.set(DEFEAT_INDEX, dealerScoreBoard.get(DEFEAT_INDEX) + 1);
        }
        if (entry.getValue().equals(ResultInfo.DEFEAT)) {
            dealerScoreBoard.set(WIN_INDEX, dealerScoreBoard.get(WIN_INDEX) + 1);
        }
        if (entry.getValue().equals(ResultInfo.DRAW)) {
            dealerScoreBoard.set(DRAW_INDEX, dealerScoreBoard.get(DRAW_INDEX) + 1);
        }
    }
}
