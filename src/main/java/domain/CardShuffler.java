package domain;

import domain.card.Card;

import java.util.List;

public interface CardShuffler {
    List<Card> shuffle(final List<Card> cards);
}
