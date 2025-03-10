package blackjack.model.card;

import java.util.Collections;
import java.util.List;

public class RandomCardShuffler implements CardShuffler {

    @Override
    public void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }
}
