package second.domain.card;

import java.util.Collections;
import java.util.List;

public class HandCards {
    private final List<Card> cards;

    public HandCards(List<Card> cards) {
        this.cards = cards;
    }

    public void drawCard(final CardDeck cardDeck) {
        cards.add(cardDeck.pickCard());
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public int calculateDefaultSum() {
        return cards.stream()
                .mapToInt(Card::extractScore)
                .sum();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
