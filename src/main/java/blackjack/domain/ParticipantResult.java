package blackjack.domain;

import blackjack.domain.participants.Name;
import java.util.LinkedHashMap;
import java.util.Map;

public class ParticipantResult {

    private final Map<Name, Integer> results;

    public ParticipantResult(final Map<Name, Integer> results) {
        this.results = new LinkedHashMap<>(results);
    }

    public Map<Name, Integer> getResults() {
        return results;
    }
}
