package domain.card;

import java.util.List;

public interface ShuffleStrategy {
    void shuffle(List<Card> deck);
}
