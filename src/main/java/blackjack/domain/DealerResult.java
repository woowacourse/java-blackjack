package blackjack.domain;

import java.util.EnumMap;
import java.util.Map;

public class DealerResult {

    private final Map<Result, Integer> result;

    public DealerResult(final ParticipantResult participantResult) {
        result = decideResult(participantResult);
    }

    private Map<Result, Integer> decideResult(final ParticipantResult participantResult) {
        Map<Result, Integer> result = new EnumMap<>(Result.class);
        for (Result value : Result.values()) {
            result.put(value, participantResult.countResult(value.getOpposite()));
        }
        return result;
    }

    public Map<Result, Integer> getResult() {
        return result;
    }
}
