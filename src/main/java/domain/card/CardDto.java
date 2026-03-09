package domain.card;

import java.util.ArrayList;
import java.util.List;

public record CardDto(List<Card> cards) {

    public String getFormattedCards() {
        List<String> cardsResult = new ArrayList<>();
        for (Card card : cards) {
            String suit = card.suitValue();
            String rank = card.symbol();
            cardsResult.add(rank + suit);
        }
        return String.join(", ", cardsResult);
    }

    public int size() {
        return cards().size();
    }
}
