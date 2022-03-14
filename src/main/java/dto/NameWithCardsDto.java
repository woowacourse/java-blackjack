package dto;

import domain.card.Card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class NameWithCardsDto {
    private final Map<String, List<Card>> nameWithCards;

    public NameWithCardsDto(final Map<String, List<Card>> nameWithCards) {
        this.nameWithCards = new HashMap<>(nameWithCards);
    }

    public Map<String, List<Card>> get() {
        return new HashMap<>(nameWithCards);
    }
}
