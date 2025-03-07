package blackjack.model.card;

import blackjack.model.card.initializer.CardDeckInitializer;

public record CardDeck(Cards cards) {

    public static CardDeck initializeFrom(final CardDeckInitializer cardDeckInitializer) {
        return new CardDeck(cardDeckInitializer.initialize());
    }

    public Cards draw(final int amount) {
        return new Cards(cards.pick(amount));
    }

}
