package domain.deck;

import java.util.List;

public interface ShuffleStrategy {
    List<Card> shuffle(List<Card> cards);
}
