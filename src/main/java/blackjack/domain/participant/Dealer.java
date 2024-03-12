package blackjack.domain.participant;

import blackjack.domain.card.Hand;
import blackjack.domain.game.Score;

public class Dealer extends Participant {

    private static final Score HIT_THRESHOLD = new Score(16);

    public Dealer() {
        super(new Hand());
    }

    public Dealer(Hand hand) {
        super(hand);
    }

    @Override
    public boolean canHit() {
        return getHandScore().canHit(HIT_THRESHOLD);
    }
}
