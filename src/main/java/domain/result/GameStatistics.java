package domain.result;

import domain.participant.Participant;
import domain.participant.Player;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameStatistics {

    private final Map<Player, GameResult> playerResult;
    private final Map<GameResult, Integer> dealerResult;

    public GameStatistics() {
        this.playerResult = new LinkedHashMap<>();
        this.dealerResult = initialize();
    }

    private EnumMap<GameResult, Integer> initialize() {
        EnumMap<GameResult, Integer> dealerResult = new EnumMap<>(GameResult.class);
        for (GameResult gameResult : GameResult.values()) {
            dealerResult.put(gameResult, 0);
        }
        return dealerResult;
    }

    public void addPlayerResult(Player player, GameResult gameResult) {
        playerResult.put(player, gameResult);
    }

    public void addDealerResult(GameResult gameResult) {
        dealerResult.put(gameResult, dealerResult.getOrDefault(gameResult, 0) + 1);
    }

    public Map<Player, GameResult> getPlayerResult() {
        return playerResult;
    }

    public GameResult findGameResultBy(Participant player) {
        return playerResult.get(player);
    }

    public Map<GameResult, Integer> getDealerResult() {
        return dealerResult;
    }
}
