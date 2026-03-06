package blackjack.service;

import blackjack.domain.ShuffleStrategy;
import blackjack.domain.TrumpCard;
import java.util.Collections;
import java.util.List;

public class RandomShuffleStrategy implements ShuffleStrategy {
    @Override
    public void shuffle(List<TrumpCard> cards) {
        Collections.shuffle(cards);
    }
}
