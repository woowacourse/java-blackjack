package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

public class Dealer extends Participant {

    public static final int DRAWING_BOUNDARY = 16;

    private final Map<Result, Integer> results;

    public Dealer() {
        this.results = initResults();
    }

    private Map<Result, Integer> initResults() {
        final Map<Result, Integer> results = new LinkedHashMap<>();
        for (final Result result : Result.values()) {
            results.put(result, 0);
        }
        return results;
    }

    public Card getFirstCard() {
        return List.copyOf(super.getCards()).get(0);
    }

    public Map<Result, Integer> getResults() {
        return this.results;
    }
}
