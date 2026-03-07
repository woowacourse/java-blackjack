package blackjack.model;

import java.util.List;

public class AceAdjustPolicy {

    private final int adjustValue;
    private final BustPolicy bustPolicy;

    public AceAdjustPolicy(int adjustValue, BustPolicy bustPolicy) {
        this.adjustValue = adjustValue;
        this.bustPolicy = bustPolicy;
    }

    public int adjust(int sum, List<Card> cards) {
        boolean containAce = cards.stream().anyMatch(card -> card.rank() == Rank.ACE);

        if (!bustPolicy.isBust(sum + adjustValue) && containAce) {
            return sum + adjustValue;
        }

        return sum;
    }
}
