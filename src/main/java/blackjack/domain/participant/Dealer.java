package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_HIT_SCORE = 16;

    private Dealer(final List<Card> cards) {
        super(Name.from(DEALER_NAME), cards);
    }

    public static Dealer from(final List<Card> cards) {
        return new Dealer(cards);
    }

    @Override
    public boolean isHit() {
        return getScore().isHit(DEALER_HIT_SCORE);
    }
}
