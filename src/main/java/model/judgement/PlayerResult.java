package model.judgement;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import model.paticipant.Player;

public class PlayerResult {

    private final Map<Player, GameStatus> result;

    public PlayerResult(Map<Player, GameStatus> result) {
        this.result = new LinkedHashMap<>(result);
    }

    public int countByStatus(GameStatus gameStatus) {
        return (int) result.values()
                .stream()
                .filter(status -> status == gameStatus)
                .count();
    }

    public Map<Player, GameStatus> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
