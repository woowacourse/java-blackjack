package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class RandomBlackjackShuffle implements BlackjackShuffle {

    @Override
    public void shuffle(final List<Card> cards) {
        Collections.shuffle(cards);
    }
}
