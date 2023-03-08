package domain;

import domain.card.Card;

import java.util.List;

@FunctionalInterface
public interface CardShuffler {
    List<Card> shuffle(final List<Card> cards);
}
