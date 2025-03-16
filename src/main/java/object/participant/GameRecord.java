package object.participant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import object.game.GameResult;

public class GameRecord {
    private final Map<GameResult, Integer> gameRecord;

    public GameRecord() {
        this.gameRecord = new HashMap<>();
    }

    public void add(GameResult result) {
        gameRecord.merge(result, 1, Integer::sum);
    }

    public Map<GameResult, Integer> getGameRecord() {
        return Collections.unmodifiableMap(gameRecord);
    }
}
