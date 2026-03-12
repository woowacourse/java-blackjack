package blackjack.domain.trump;

import java.util.Collections;
import java.util.List;

public class RandomSortBehavior implements SortBehavior {

    @Override
    public void shuffle(final List<Card> cards) {
        Collections.shuffle(cards);
    }
}
