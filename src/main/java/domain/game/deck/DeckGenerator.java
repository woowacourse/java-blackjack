package domain.game.deck;

import domain.card.Card;

@FunctionalInterface
public interface DeckGenerator {
    Deck generate(Card... card);
}
