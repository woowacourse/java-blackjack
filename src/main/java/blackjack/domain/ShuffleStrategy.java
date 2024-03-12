package blackjack.domain;

import java.util.List;

public interface ShuffleStrategy {

    void shuffle(final List<Card> cards);

}
