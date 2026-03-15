package domain.result;

import domain.participant.Player;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameStatistics {

    private final Map<Player, GameResult> playerResult;
    private final Map<GameResult, Integer> dealerResult;

    public GameStatistics(Map<Player, GameResult> gameResultMap) {
        this.playerResult = gameResultMap;
        this.dealerResult = initializeMap();
        initializeDealerResult();
    }

    private Map<GameResult, Integer> initializeMap() {
        EnumMap<GameResult, Integer> dealerResult = new EnumMap<>(GameResult.class);

        for (GameResult gameResult : GameResult.values()) {
            dealerResult.put(gameResult, 0);
        }
        return dealerResult;
    }

    public void initializeDealerResult() {
        for (Map.Entry<Player, GameResult> playerResult : playerResult.entrySet()) {
            addDealerResult(playerResult.getValue().reverse());
        }
    }

    public void addDealerResult(GameResult gameResult) {
        dealerResult.put(gameResult, dealerResult.getOrDefault(gameResult, 0) + 1);
    }

    public Map<String, String> getPlayerResult() {
        Map<String, String> result = new LinkedHashMap<>();
        for (Map.Entry<Player, GameResult> entry : playerResult.entrySet()) {
            result.put(entry.getKey().getName(), entry.getValue().getDescription());
        }
        return Collections.unmodifiableMap(result);
    }

    public Map<String, Integer> getDealerResult() {
        Map<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<GameResult, Integer> entry : dealerResult.entrySet()) {
            String description = entry.getKey().getDescription();
            int count = entry.getValue();
            result.put(description, result.getOrDefault(description, 0) + count);
        }
        return Collections.unmodifiableMap(result);
    }
}
