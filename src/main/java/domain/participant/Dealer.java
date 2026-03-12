package domain.participant;

import static domain.constant.GameRule.DEALER_HIT_CRITERION;

public class Dealer extends Participant {

    @Override
    public boolean checkScoreUnderCriterion() {
        return cardBoard.calculateScore() < DEALER_HIT_CRITERION;
    }
}
