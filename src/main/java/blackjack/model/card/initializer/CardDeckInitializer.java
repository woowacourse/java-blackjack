package blackjack.model.card.initializer;

import blackjack.model.card.Cards;

@FunctionalInterface
public interface CardDeckInitializer {

    Cards initialize();
}
