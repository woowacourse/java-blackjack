package domain;

import java.util.Map;

import domain.result.Result;

public class PlayerResult {
    private Map<String, Result> playerResult;

    public PlayerResult(Map<String, Result> playerResult) {
        this.playerResult = playerResult;
    }

    public Map<String, Result> getResult() {
        return playerResult;
    }
}
