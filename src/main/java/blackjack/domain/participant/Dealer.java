package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_HIT_SCORE = 16;

    public Dealer(final List<Card> cards) {
        super(new Name(DEALER_NAME), cards);
    }

    @Override
    public boolean isHit() {
        return getScore().isHit(DEALER_HIT_SCORE);
    }
}
