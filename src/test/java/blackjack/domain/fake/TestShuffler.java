package blackjack.domain.fake;

import blackjack.domain.Shuffler;
import blackjack.domain.card.Card;
import java.util.List;

public class TestShuffler implements Shuffler {
    @Override
    public List<Card> shuffle(List<Card> cards) {
        return cards;
    }
}
