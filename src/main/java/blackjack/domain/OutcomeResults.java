package blackjack.domain;

import java.util.EnumMap;
import java.util.Map;

public class OutcomeResults {

    private final Map<Outcome, Integer> outcomes;

    public OutcomeResults() {
        outcomes = new EnumMap<>(Outcome.class);
    }

    public void increase(Outcome outcome) {
        outcomes.put(outcome, outcomes.getOrDefault(outcome, 0) + 1);
    }

    public Map<Outcome, Integer> getOutcomes() {
        return outcomes;
    }
}
