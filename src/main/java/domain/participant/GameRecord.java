package domain.participant;

import java.util.HashMap;
import java.util.Map;

public class GameRecord {
    private final Map<BattleResult, Integer> gameRecord;

    public GameRecord() {
        this.gameRecord = new HashMap<>();
    }
}
