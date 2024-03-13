package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private final Map<String, Boolean> gameResult;

    public GameResult(Map<String, Boolean> gameResult) {
        this.gameResult = gameResult;
    }

    public int countDealerWin() {
        int count = 0;
        for (Boolean winOrLose : gameResult.values()) {
            if (!winOrLose) {
                count++;
            }
        }
        return count;
    }

    public int countDealerLose() {
        int count = 0;
        for (Boolean winOrLose : gameResult.values()) {
            if (winOrLose) {
                count++;
            }
        }
        return count;
    }

    public Map<String, String> getResult() {
        Map<String, String> result = new LinkedHashMap<>();
        for (String playerName : gameResult.keySet()) {
            Boolean winOrLose = gameResult.get(playerName);
            result.put(playerName, getWinOrLose(winOrLose));
        }
        return result;
    }

    public String getWinOrLose(boolean winOrLose) {
        if (winOrLose) {
            return "승";
        }
        return "패";
    }
}
