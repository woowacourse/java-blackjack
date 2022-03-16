package blackjack.domain;

import java.util.Map;

public class OutcomeResults {

    private final Map<Outcome, Integer> outcomes;

    public OutcomeResults(Map<Outcome, Integer> outcomes) {
        this.outcomes = outcomes;
    }

    public int getCount(Outcome outcome) {
        return outcomes.get(outcome);
    }
}
