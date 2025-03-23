package blackjack.object.fake;

import blackjack.object.card.Card;
import blackjack.object.card.Shuffler;
import java.util.List;

public class TestShuffler implements Shuffler {
    @Override
    public List<Card> shuffle(List<Card> cards) {
        return cards;
    }
}
