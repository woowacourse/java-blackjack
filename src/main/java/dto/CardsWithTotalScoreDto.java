package dto;

import domain.card.Card;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class CardsWithTotalScoreDto {
    private final Map<NameWithCardsDto, Integer> cardsWithTotalScore = new LinkedHashMap<>();

    public CardsWithTotalScoreDto(final Map<String, List<Card>> cardsWithName, final Map<String, Integer> totalScoreWithName) {
        for (final Entry<String, List<Card>> entry : cardsWithName.entrySet()) {
            final NameWithCardsDto nameWithCards = new NameWithCardsDto(Map.of(entry.getKey(), entry.getValue()));
            cardsWithTotalScore.put(nameWithCards, totalScoreWithName.get(entry.getKey()));
        }
    }

    public Map<NameWithCardsDto, Integer> get() {
        return new LinkedHashMap<>(cardsWithTotalScore);
    }
}
