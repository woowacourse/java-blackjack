package domain.participant;

import static constant.GameRule.DEALER_HIT_CRITERION;

public class Dealer extends Participant {
    @Override
    public boolean canDraw() {
        return getScore() < DEALER_HIT_CRITERION;
    }
}
