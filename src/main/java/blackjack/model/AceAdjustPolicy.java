package blackjack.model;

import java.util.List;

public class AceAdjustPolicy {
    private final BustPolicy bustPolicy;

    public AceAdjustPolicy(BustPolicy bustPolicy) {
        this.bustPolicy = bustPolicy;
    }

    public int adjust(int sum, List<Card> cards) {
        boolean containAce = cards.stream().anyMatch(x -> x.rank() == Rank.ACE);
        if (!bustPolicy.isBust(sum + 10) && containAce) {
            return sum + 10;
        }
        return sum;
    }
}
