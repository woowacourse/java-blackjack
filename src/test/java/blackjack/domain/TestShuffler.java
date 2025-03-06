package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class TestShuffler implements Shuffler {
    @Override
    public List<Card> shuffle(List<Card> cards) {
        return cards;
    }
}
