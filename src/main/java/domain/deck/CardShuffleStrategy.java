package domain.deck;

import domain.card.Card;

import java.util.List;

public interface CardShuffleStrategy {
    void shuffle(List<Card> cards);
}
