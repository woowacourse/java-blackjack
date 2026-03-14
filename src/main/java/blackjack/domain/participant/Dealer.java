package blackjack.domain.participant;

import blackjack.domain.Hand;
import blackjack.domain.Name;

public class Dealer extends Participant {
    private static final int DEALER_HIT_THRESHOLD = 16;

    private Dealer(Name name, Hand hand) {
        super(name, hand);
    }

    public static Dealer of() {
        return new Dealer(Name.of("딜러"), Hand.init());
    }

    @Override
    public boolean canHit() {
        return score() <= DEALER_HIT_THRESHOLD;
    }
}
