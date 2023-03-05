package domain.strategy;

import domain.card.Card;

import java.util.List;

public interface ShuffleStrategy {

    void shuffle(List<Card> cards);
}
