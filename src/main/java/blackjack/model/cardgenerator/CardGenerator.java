package blackjack.model.cardgenerator;

import blackjack.model.card.Card;

@FunctionalInterface
public interface CardGenerator {
    Card pick();
}
