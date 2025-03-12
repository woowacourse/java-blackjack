package object.participant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import object.game.BattleResult;

public class GameRecord {
    private final Map<BattleResult, Integer> gameRecord;

    public GameRecord() {
        this.gameRecord = new HashMap<>();
    }

    public void add(BattleResult result) {
        gameRecord.merge(result, 1, Integer::sum);
    }

    public Map<BattleResult, Integer> getGameRecord() {
        return Collections.unmodifiableMap(gameRecord);
    }
}
