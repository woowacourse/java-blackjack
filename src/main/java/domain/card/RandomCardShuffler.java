package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomCardShuffler implements CardShuffler {

    @Override
    public List<Card> shuffle(final List<Card> cards) {
        final List<Card> target = new ArrayList<>(cards);
        Collections.shuffle(target);
        return target;
    }
}
