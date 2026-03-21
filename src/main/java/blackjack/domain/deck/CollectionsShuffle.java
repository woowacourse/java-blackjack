package blackjack.domain.deck;

import blackjack.domain.card.Card;
import java.util.Collections;
import java.util.List;

public class CollectionsShuffle implements ShuffleStrategy {

    @Override
    public void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }

}
