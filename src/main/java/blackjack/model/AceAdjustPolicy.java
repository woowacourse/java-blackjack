package blackjack.model;

import java.util.List;

public class AceAdjustPolicy {

    private static final int ADJUST_VALUE = 10;

    private final BustPolicy bustPolicy;

    public AceAdjustPolicy(BustPolicy bustPolicy) {
        this.bustPolicy = bustPolicy;
    }

    public int adjust(int sum, List<Card> cards) {
        boolean containAce = cards.stream().anyMatch(x -> x.rank() == Rank.ACE);
        if (!bustPolicy.isBust(sum + ADJUST_VALUE) && containAce) {
            return sum + ADJUST_VALUE;
        }
        return sum;
    }
}
