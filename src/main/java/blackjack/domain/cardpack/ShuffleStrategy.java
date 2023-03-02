package blackjack.domain.cardpack;

import blackjack.domain.card.Card;
import java.util.List;

@FunctionalInterface
public interface ShuffleStrategy {
    void shuffle(final List<Card> cards);
}
