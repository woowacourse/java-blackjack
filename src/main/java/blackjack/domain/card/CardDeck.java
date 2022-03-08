package blackjack.domain.card;

import java.util.List;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck(CardsGenerator generator) {
        cards = generator.generate();
    }

    public Card draw() {
        return cards.get(0);
    }
}
