package blackjack.model.card.initializer;

import blackjack.model.card.BlackJackCards;

@FunctionalInterface
public interface CardDeckInitializer {

    BlackJackCards initialize();
}
