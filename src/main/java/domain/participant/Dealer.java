package domain.participant;

import static constant.GameRule.DEALER_HIT_CRITERION;

public class Dealer extends Participant {

    public boolean mustHit() {
        return hand.calculateScore() < DEALER_HIT_CRITERION;
    }
}
