package blackjack.domain.deck.shuffle;

import java.util.Collections;
import java.util.List;

public class RandomShuffle implements Shuffle {

    @Override
    public void shuffle(List<?> cards) {
       Collections.shuffle(cards);
    }
}
