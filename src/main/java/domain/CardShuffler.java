package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardShuffler implements ShuffleStrategy {

    @Override
    public void shuffle(final List<Card> cards) {
        List<Card> shuffleCards = new ArrayList<>(cards);
        Collections.shuffle(shuffleCards);
    }
}
