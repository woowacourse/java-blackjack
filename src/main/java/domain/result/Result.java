package domain.result;

import domain.participant.Player;

import java.util.Collections;
import java.util.HashMap;
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

    public int calculateProfitFor(Player player, int betAmount) {
        return findResultInfoFor(player).calculateProfit(betAmount);
    }

    private ResultInfo findResultInfoFor(Player player) {
        return gameResult.get(player.getName());
    }
}
