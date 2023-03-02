package blackjack.domain.util;

import blackjack.domain.card.Card;
import blackjack.domain.cardpack.ShuffleStrategy;
import java.util.List;

public class NoviceShuffleStrategy implements ShuffleStrategy {

    @Override
    public void shuffle(final List<Card> cards) {
        cards.add(cards.remove(0));
    }

}
