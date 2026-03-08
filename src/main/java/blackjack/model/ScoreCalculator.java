package blackjack.model;

import java.util.List;

public class ScoreCalculator {
    private static final int ACE_ADJUST_AMOUNT = 10;

    private final BustPolicy bustPolicy;

    public ScoreCalculator(BustPolicy bustPolicy) {
        this.bustPolicy = bustPolicy;
    }

    public Score calculateOptimalTotal(List<Card> cards) {
        int sum = cards.stream().mapToInt(Card::getValue).sum();

        if (hasAce(cards) && canApplyAceAmount(sum)) {
            return new Score(sum + ACE_ADJUST_AMOUNT);
        }
        return new Score(sum);
    }

    private static boolean hasAce(List<Card> cards) {
        return cards.stream().anyMatch(x -> x.rank() == Rank.ACE);
    }

    private boolean canApplyAceAmount(int sum) {
        return !bustPolicy.isBust(new Score(sum + ACE_ADJUST_AMOUNT));
    }
}
