package domain.result;

import domain.participant.Player;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameStatistics {

    private final Map<Player, GameResult> playerResult;
    private final Map<String, Integer> dealerResult;

    public GameStatistics() {
        this.playerResult = new LinkedHashMap<>();
        this.dealerResult = initialize();
    }

    private LinkedHashMap<String, Integer> initialize() {
        LinkedHashMap<String, Integer> dealerResult = new LinkedHashMap<>();
        for (String gameResult : Arrays.stream(GameResult.values()).map(GameResult::getDescription).toList()) {
            dealerResult.put(gameResult, 0);
        }
        return dealerResult;
    }

    public void addPlayerResult(Player player, GameResult gameResult) {
        playerResult.put(player, gameResult);
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
