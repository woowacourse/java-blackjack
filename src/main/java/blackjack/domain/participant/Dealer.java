package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {

    public static final int DRAWING_BOUNDARY = 16;
    private static final int INCREASING_NUMBER = 1;

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
        final List<Card> cards = new ArrayList<>(super.getCards());
        return cards.get(0);
    }

    public Map<Result, Integer> getResults() {
        return this.results;
    }

    public void setResults(final Result result) {
        results.put(result, results.get(result) + INCREASING_NUMBER);
    }
}
