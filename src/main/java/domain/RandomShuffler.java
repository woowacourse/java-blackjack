package domain;

import java.util.Collections;
import java.util.List;

public class RandomShuffler implements Shuffler {
    @Override
    public void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }
}
