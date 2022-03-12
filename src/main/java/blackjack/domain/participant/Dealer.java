package blackjack.domain.participant;

import blackjack.domain.result.ScoreCalculator;

public class Dealer extends Participant {

    private static final int MIN_SUM_STANDARD = 16;

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
