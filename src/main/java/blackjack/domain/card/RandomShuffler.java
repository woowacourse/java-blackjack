package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomShuffler implements Shuffler {
    @Override
    public List<Card> shuffle(List<Card> cards) {
        List<Card> newCards = new ArrayList<>(cards);
        Collections.shuffle(newCards);
        return newCards;
    }
}
