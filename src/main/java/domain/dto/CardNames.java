package domain.dto;

import domain.card.Card;
import java.util.List;
import java.util.stream.Collectors;

public class CardNames {
    private final List<String> cardNames;

    public CardNames(final List<String> cardNames) {
        this.cardNames = cardNames;
    }

    public List<String> getCardNames(){
        return cardNames;
    }
}
