package domain.participant;

import domain.GameResultStatus;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private final Map<Player, GameResultStatus> result;

    public GameResult() {
        this.result = new LinkedHashMap<>();
    }

    public GameResult(Map<Player, GameResultStatus> result) {
        this.result = Map.copyOf(result);
    }

    public void put(final Player player, final GameResultStatus resultStatus) {
        result.put(player, resultStatus);
    }

    public Map<Player, GameResultStatus> getResult() {
        return Map.copyOf(result);
    }

    public GameResult ofDealer(){
        Map<Player, GameResultStatus> dealerResult = new HashMap<>();
        result.forEach((key, value) -> {
            dealerResult.put(key, value.opposite());
        });
        return new GameResult(dealerResult);
    }
}
