package domain.participant;

import static constant.GameRule.DEALER_HIT_CRITERION;

public class Dealer extends Participant {

    @Override
    public boolean checkScoreUnderCriterion() {
        return hand.calculateScore() < DEALER_HIT_CRITERION;
    }
}
