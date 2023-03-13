package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public final class Dealer extends Participant {

    private static final int DEALER_HIT_SCORE = 16;

    private Dealer(final List<Card> cards) {
        super(Name.from(Name.DEALER_NAME), cards);
    }

    public static Dealer from(final List<Card> cards) {
        return new Dealer(cards);
    }

    @Override
    public boolean isHit() {
        return getScore().canHit(DEALER_HIT_SCORE);
    }
}
