package blackjack.domain.participant;

import blackjack.domain.result.ScoreCalculator;

public class Dealer extends Participant {

    private static final int MIN_SUM_STANDARD = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean checkUnderSumStandard() {
        return ScoreCalculator.cardSum(cards) <= MIN_SUM_STANDARD;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public boolean isUser() {
        return false;
    }
}
