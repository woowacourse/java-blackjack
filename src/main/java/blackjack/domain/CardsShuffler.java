package blackjack.domain;

import java.util.List;

@FunctionalInterface
public interface CardsShuffler {
    void shuffle(List<Card> cards);
}
