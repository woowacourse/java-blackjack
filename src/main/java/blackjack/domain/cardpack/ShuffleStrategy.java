package blackjack.domain.cardpack;

import blackjack.domain.card.Card;

import java.util.List;

public interface ShuffleStrategy {
    void shuffle(final List<Card> cards);
}
