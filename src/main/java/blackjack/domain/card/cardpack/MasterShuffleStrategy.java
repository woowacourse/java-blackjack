package blackjack.domain.card.cardpack;

import blackjack.domain.card.Card;

import java.util.Collections;
import java.util.List;

public class MasterShuffleStrategy implements ShuffleStrategy {

    @Override
    public void shuffle(final List<Card> cards) {
        Collections.shuffle(cards);
    }
}
