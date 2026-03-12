package domain.result;

import domain.participant.Player;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameStatistics {

    private final Map<Player, GameResult> playerResult;
    private final Map<String, Integer> dealerResult;

    public GameStatistics(Map<Player, GameResult> gameResultMap) {
        this.playerResult = gameResultMap;
        this.dealerResult = initializeMap();
        initializeDealerResult();
    }


    private LinkedHashMap<String, Integer> initializeMap() {
        LinkedHashMap<String, Integer> dealerResult = new LinkedHashMap<>();
        for (String gameResult : Arrays.stream(GameResult.values()).map(GameResult::getDescription).toList()) {
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
        dealerResult.put(gameResult.getDescription(), dealerResult.getOrDefault(gameResult.getDescription(), 0) + 1);
    }

    public Map<Player, GameResult> getPlayerResult() {
        return playerResult;
    }

    public Map<String, Integer> getDealerResult() {
        return dealerResult;
    }
}
