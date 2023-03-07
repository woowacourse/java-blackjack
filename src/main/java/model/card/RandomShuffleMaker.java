package model.card;

import java.util.Collections;
import java.util.List;

public class RandomShuffleMaker implements ShuffleStrategy{

    @Override
    public void shuffle(final List<Card> cards) {
        Collections.shuffle(cards);
    }
}
