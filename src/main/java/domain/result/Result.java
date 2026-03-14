package domain.result;

import domain.participant.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {
    private static final int INIT_COUNT = 0;

    private final Map<String, ResultInfo> gameResult;

    public Result() {
        this.gameResult = new HashMap<>();
    }

    public Map<String, ResultInfo> getGameResult() {
        return Collections.unmodifiableMap(gameResult);
    }

    public void setPlayerResult(String name, ResultInfo info) {
        gameResult.put(name, info);
    }

    public List<Integer> dealerResult() {
        List<Integer> dealerResultBoard = new ArrayList<>(List.of(INIT_COUNT, INIT_COUNT, INIT_COUNT));
        for (Map.Entry<String, ResultInfo> entry : gameResult.entrySet()) {
            calculateDealerResultBoard(entry, dealerResultBoard);
        }
        return dealerResultBoard;
    }

    public ResultInfo findPlayerResult(Player player) {
        return gameResult.get(player.getName());
    }

    private void calculateDealerResultBoard(Map.Entry<String, ResultInfo> entry, List<Integer> dealerResultBoard) {
        if (entry.getValue().equals(ResultInfo.WIN)) {
            dealerResultBoard.set(ResultInfo.DEFEAT.getCode(), dealerResultBoard.get(ResultInfo.DEFEAT.getCode()) + 1);
        }
        if (entry.getValue().equals(ResultInfo.DEFEAT)) {
            dealerResultBoard.set(ResultInfo.WIN.getCode(), dealerResultBoard.get(ResultInfo.WIN.getCode()) + 1);
        }
        if (entry.getValue().equals(ResultInfo.DRAW)) {
            dealerResultBoard.set(ResultInfo.DRAW.getCode(), dealerResultBoard.get(ResultInfo.DRAW.getCode()) + 1);
        }
    }
}
