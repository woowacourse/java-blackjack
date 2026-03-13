package domain.deck;

import domain.card.Card;

import java.util.List;

@FunctionalInterface
public interface Shuffle {

    void shuffle(List<Card> cards);
}
