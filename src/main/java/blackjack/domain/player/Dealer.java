package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.Collections;
import java.util.List;

public class Dealer extends Player {

    public static final int DEALER_SCORE_PIVOT = 16;

    public Dealer(final Cards cards) {
        super(cards, new Name("딜러"));
    }

    public boolean canDraw() {
        return getScore() <= DEALER_SCORE_PIVOT;
    }

    @Override
    public List<Card> getInitCards() {
        return Collections.singletonList(hand.getFirstCard());
    }
}
