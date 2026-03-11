package domain.card;

import java.util.List;
import java.util.stream.Collectors;

public record CardsSnapshot(List<Card> cards) {

    public String getFormattedCards() {
        return cards.stream()
                .map(card -> card.suit() + card.symbol())
                .collect(Collectors.joining(", "));
    }

    public int size() {
        return cards().size();
    }
}
