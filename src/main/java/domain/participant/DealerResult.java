package domain.participant;

import java.util.EnumMap;
import java.util.Map;

import domain.GameResultStatus;

public class DealerResult {

    private final EnumMap<GameResultStatus, Integer> result;

    public DealerResult(final EnumMap<GameResultStatus, Integer> result) {
        this.result = new EnumMap<>(result);
    }

    public void put(final GameResultStatus resultStatus) {
        result.put(resultStatus, result.getOrDefault(resultStatus, 0) + 1);
    }

    public Map<GameResultStatus, Integer> getResult() {
        return Map.copyOf(result);
    }
}
