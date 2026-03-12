package blackjack.model;

public class ScoreCalculator {
    private static final int ACE_ADJUST_AMOUNT = 10;

    private final BustPolicy bustPolicy;

    public ScoreCalculator(BustPolicy bustPolicy) {
        this.bustPolicy = bustPolicy;
    }

    public Score calculateOptimalTotalOf(final int handSum, final boolean hasAce) {
        if (hasAce && canApplyAceAmount(handSum)) {
            return new Score(handSum + ACE_ADJUST_AMOUNT);
        }
        return new Score(handSum);
    }

    private boolean canApplyAceAmount(final int sum) {
        return !bustPolicy.isBust(new Score(sum + ACE_ADJUST_AMOUNT));
    }
}
