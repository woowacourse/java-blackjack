package blackjack.domain.strategy;

import blackjack.domain.card.Card;

import java.util.Collections;
import java.util.List;

public class ReverseCardShuffleStrategy implements CardShuffleStrategy {

    @Override
    public void shuffle(final List<Card> cards) {
        Collections.reverse(cards);
    }
}
