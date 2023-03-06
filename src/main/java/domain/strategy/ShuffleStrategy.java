package domain.strategy;

import domain.card.Card;

import java.util.List;

public interface ShuffleStrategy {

    void shuffle(final List<Card> cards);
}
