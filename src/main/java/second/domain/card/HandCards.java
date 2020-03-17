package second.domain.card;

import second.domain.IHandCards;

import java.util.Collections;
import java.util.List;

public class HandCards implements IHandCards {
    private final List<Card> cards;

    public HandCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public void drawCard(final CardDeck cardDeck) {
        cards.add(cardDeck.pickCard());
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
