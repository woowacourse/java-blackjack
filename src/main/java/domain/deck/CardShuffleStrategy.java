package domain.deck;

import domain.card.Card;

import java.util.List;

public interface CardShuffleStrategy {
    List<Card> shuffle(List<Card> cards);
}
