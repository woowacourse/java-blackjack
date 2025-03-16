package blackjack.card;

import blackjack.card.initializer.CardsInitializer;
import java.util.List;

public class CardDeck {
    private final List<Card> cards;

    public CardDeck(CardsInitializer cardsInitializer) {
        this.cards = cardsInitializer.initialize();
    }

    public Card drawCard() {
        return cards.removeLast();
    }
}
