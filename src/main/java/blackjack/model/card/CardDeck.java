package blackjack.model.card;

import blackjack.model.card.initializer.DefaultCardDeckInitializer;

public record CardDeck(Cards cards) {

    public static CardDeck initializeFrom(final DefaultCardDeckInitializer defaultCardDeckInitializer) {
        return new CardDeck(defaultCardDeckInitializer.initialize());
    }

    public Cards draw(final int amount) {
        return new Cards(cards.pick(amount));
    }

}
