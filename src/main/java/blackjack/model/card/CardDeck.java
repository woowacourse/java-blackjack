package blackjack.model.card;

import blackjack.model.card.initializer.CardDeckInitializer;

public record CardDeck(BlackJackCards blackJackCards) {

    public static CardDeck initializeFrom(final CardDeckInitializer cardDeckInitializer) {
        return new CardDeck(cardDeckInitializer.initialize());
    }

    public BlackJackCards draw(final int amount) {
        return new BlackJackCards(blackJackCards.pick(amount));
    }

}
