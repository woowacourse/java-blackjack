package card;

import java.util.Collections;
import java.util.List;

public class RandomShuffleMachine implements ShuffleMachine {

    @Override
    public void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }
}
