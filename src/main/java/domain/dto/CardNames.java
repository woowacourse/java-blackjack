package domain.dto;

import domain.card.Card;
import java.util.List;
import java.util.stream.Collectors;

public class CardNames {
    private final List<String> cardNames;

    private CardNames(List<String> cardNames) {
        this.cardNames = cardNames;
    }

    public static CardNames of(List<Card> cards){
        List<String> cardNames = cards.stream()
                .map(card -> card.getCardName())
                .collect(Collectors.toUnmodifiableList());
        return new CardNames(cardNames);
    }
}
