package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.Collections;
import java.util.List;

public class CardShuffler implements Shuffler {
    @Override
    public List<Card> shuffle(final List<Card> cards) {
        Collections.shuffle(cards);
        return cards;
    }
}
