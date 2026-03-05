package domain;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class GameStatistics {

    private Map<Participant, GameResult> playerResult;
    private Map<GameResult, Integer> dealerResult;

    public GameStatistics() {
        this.playerResult = new HashMap<>();
        this.dealerResult = new EnumMap<>(GameResult.class);
    }

    public void addPlayerResult(Participant participant, GameResult gameResult) {
        playerResult.put(participant, gameResult);
    }

    public Map<Participant, GameResult> getPlayerResult() {
        return playerResult;
    }

    public GameResult findGameResultByPlayer(Participant player) {
        return playerResult.get(player);
    }
}
