package model.card;

import java.util.List;

@FunctionalInterface
public interface ShuffleStrategy {

    void shuffle(final List<Card> cards);
}
