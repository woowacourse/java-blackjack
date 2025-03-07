package blackjack.domain.fake;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shuffler;
import java.util.List;

public class TestShuffler implements Shuffler {
    @Override
    public List<Card> shuffle(List<Card> cards) {
        return cards;
    }
}
