package blackjack.domain;

import blackjack.domain.player.Player;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class PlayerOutcomeResults {

    private final Map<Player, Outcome> results;

    public PlayerOutcomeResults(Map<Player, Outcome> results) {
        this.results = results;
    }

    public OutcomeResults getDealerResult() {
        Map<Outcome, Integer> outcomes = new EnumMap<>(Outcome.class);
        outcomes.put(Outcome.WIN, getCount(Outcome.LOSE));
        outcomes.put(Outcome.LOSE, getCount(Outcome.WIN));
        outcomes.put(Outcome.DRAW, getCount(Outcome.DRAW));
        return new OutcomeResults(outcomes);
    }

    private int getCount(Outcome outcome) {
        return (int) results.values().stream()
                .filter(value -> value.equals(outcome))
                .count();
    }

    public Map<Player, Outcome> getResults() {
        return Collections.unmodifiableMap(results);
    }
}
