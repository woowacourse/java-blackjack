package domain.strategy;

import domain.card.Card;

import java.util.List;

public interface ShuffleStrategy {

    List<Card> makeShuffledCards(final List<Card> cards);
}
