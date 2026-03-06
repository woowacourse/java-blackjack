package blackjack.domain;

import java.util.List;

public interface ShuffleStrategy {
    void shuffle(List<TrumpCard> cards);
}
