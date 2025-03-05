package blackjack.domian;

import java.util.Collections;
import java.util.List;

public class RandomCardsShuffler implements CardsShuffler {
    @Override
    public void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }
}
