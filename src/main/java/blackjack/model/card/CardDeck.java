package blackjack.model.card;

import blackjack.model.card.initializer.DefaultCardDeckInitializer;

public class CardDeck {

    private final Cards cards;

    public CardDeck(final Cards cards) {
        this.cards = cards;
    }

    public static CardDeck initializeFrom(final DefaultCardDeckInitializer defaultCardDeckInitializer) {
        return new CardDeck(defaultCardDeckInitializer.initialize());
    }

    public Cards draw(final int amount) {
        return new Cards(cards.pick(amount));
    }

    public Cards getCards() {
        return cards;
    }
}
