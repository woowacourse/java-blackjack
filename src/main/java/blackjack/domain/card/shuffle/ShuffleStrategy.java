package blackjack.domain.card.shuffle;

import blackjack.domain.card.Card;

import java.util.List;

public interface ShuffleStrategy {
    List<Card> shuffle(final List<Card> cards);
}
