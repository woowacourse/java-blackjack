package strategy;

import domain.Card;

import java.util.List;

public interface ShuffleStrategy {
    void shuffle(final List<Card> cards);
}
