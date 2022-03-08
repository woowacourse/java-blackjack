package blackjack.domain.card;

import java.util.List;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck(CardsGenerator generator) {
        cards = generator.generate();
    }

    public Card draw() {
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
