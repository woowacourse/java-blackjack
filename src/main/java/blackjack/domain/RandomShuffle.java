package blackjack.domain;

import java.util.Collections;
import java.util.List;

public class RandomShuffle implements Shuffle {

    @Override
    public void shuffle(final List<?> before) {
        Collections.shuffle(before);
    }
}
