package dto;

import domain.card.Card;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class CardsWithTotalScore {
    private final Map<Map<String, List<Card>>, Integer> cardsWithTotalScore = new LinkedHashMap<>();

    public CardsWithTotalScore(final Map<String, List<Card>> cardsWithName,
                               final Map<String, Integer> totalScoreWithName) {
        for (Entry<String, List<Card>> entry : cardsWithName.entrySet()) {
            cardsWithTotalScore.put(Map.of(entry.getKey(), entry.getValue()), totalScoreWithName.get(entry.getKey()));
        }
    }

    public Map<Map<String, List<Card>>, Integer> get() {
        return new LinkedHashMap<>(cardsWithTotalScore);
    }
}
