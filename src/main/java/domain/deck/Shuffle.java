package domain.deck;

import domain.Card;

import java.util.List;

@FunctionalInterface
public interface Shuffle {

    void shuffle(List<Card> cards);
}
