package blackjack.domain.player;

import blackjack.domain.card.Hand;

public class Dealer extends Participant {

    private static final int HIT_THRESHOLD = 17;

    public Dealer(Hand hand) {
        super(hand);
    }

    @Override
    protected boolean canHit(int score) {
        return score < HIT_THRESHOLD;
    }
}
