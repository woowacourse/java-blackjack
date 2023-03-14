package blackjack.domain.card.cardpack;

import blackjack.domain.card.Card;

import java.util.List;

public class NoviceShuffleStrategy implements ShuffleStrategy {

    @Override
    public void shuffle(final List<Card> cards) {
        cards.add(cards.remove(0));
    }

}
