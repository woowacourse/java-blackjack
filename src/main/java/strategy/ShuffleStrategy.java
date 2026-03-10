package strategy;

import domain.Card;

import java.util.List;

public interface ShuffleStrategy {
    void shuffle(List<Card> cards);
}
