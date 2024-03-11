package domain.participant;

import java.util.EnumMap;
import java.util.Map;

import domain.GameResultStatus;

public class DealerResult {

    private final Map<GameResultStatus, Integer> result;

    public DealerResult(final Map<GameResultStatus, Integer> result) {
        this.result = new EnumMap<>(result);
    }

    public void put(final GameResultStatus resultStatus) {
        result.put(resultStatus, result.getOrDefault(resultStatus, 0) + 1);
    }
}
