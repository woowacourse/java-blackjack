package domain.card.deck;

import domain.card.Card;
import java.util.List;

@FunctionalInterface
public interface CardShuffleStrategy {
    void shuffle(List<Card> cards);
}
