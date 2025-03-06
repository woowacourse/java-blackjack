package blackjack.domain;

import blackjack.domain.card.BlackjackShuffle;
import blackjack.domain.card.Card;

import java.util.Comparator;
import java.util.List;

public class SortShuffle implements BlackjackShuffle {

    @Override
    public void shuffle(List<Card> cards) {
        cards.sort(Comparator
                .comparing(Card::getNumber)
                .thenComparing(Card::getShape));
    }
}
