package strategy;

import domain.deck.Card;

import java.util.List;

public interface ShuffleStrategy {
    void shuffle(final List<Card> cards);
}
