package blakcjack.domain.shufflestrategy;

import blakcjack.domain.card.Card;

import java.util.List;

@FunctionalInterface
public interface ShuffleStrategy {
    void shuffle(List<Card> cards);
}
