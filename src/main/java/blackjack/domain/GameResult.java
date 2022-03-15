package blackjack.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public enum GameResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String result;

    GameResult(String result) {
        this.result = result;
    }

    public static GameResult getPairResult(GameResult gameResult) {
        if(gameResult == WIN) {
            return LOSE;
        }
        if(gameResult == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public static Map<GameResult, Integer> toGameResultMap(List<GameResult> gameResults) {
        Map<GameResult, Integer> result = new EnumMap<>(GameResult.class);
        for (GameResult gameResult : GameResult.values()) {
            result.put(gameResult, result.getOrDefault(gameResult, 0) + 1);
        }
        return result;
    }

    public String getResult() {
        return result;
    }
}
