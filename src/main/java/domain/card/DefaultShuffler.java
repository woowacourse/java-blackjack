package domain.card;

import java.util.Collections;
import java.util.List;

public class DefaultShuffler implements Shuffler {

    @Override
    public void shuffle(final List<Card> list) {
        Collections.shuffle(list);
    }
}
