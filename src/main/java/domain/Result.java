package domain;

import java.util.Map;

public class Result {
    private final Map<String, Boolean> gameResult;

    public Result(Map<String, Boolean> gameResult) {
        this.gameResult = gameResult;
    }

    public Map<String, Boolean> getGameResult() {
        return gameResult;
    }
}
