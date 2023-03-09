package domain.dto;

import java.util.List;

public class CardNames {
    private final List<String> cardNames;

    public CardNames(final List<String> cardNames) {
        this.cardNames = cardNames;
    }

    public List<String> getCardNames() {
        return cardNames;
    }
}
