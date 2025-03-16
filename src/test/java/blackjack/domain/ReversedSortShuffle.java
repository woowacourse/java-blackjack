package blackjack.domain;

import blackjack.domain.card.BlackjackShuffle;
import blackjack.domain.card.Card;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReversedSortShuffle implements BlackjackShuffle {

    @Override
    public void shuffle(List<Card> cards) {
        cards.sort(Comparator
                .comparing(Card::getValue)
                .thenComparing(Card::getShape));
        Collections.reverse(cards);
    }
}
