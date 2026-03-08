package blackjack.model;

import java.util.List;

public class ScoreCalculator {
    private static final int ACE_ADJUST_AMOUNT = 10;

    private final BustPolicy bustPolicy;

    public ScoreCalculator(BustPolicy bustPolicy) {
        this.bustPolicy = bustPolicy;
    }

    public Score calculate(List<Card> cards) {
        int sum = cards.stream().mapToInt(Card::getValue).sum();
        boolean hasAce = cards.stream().anyMatch(x -> x.rank() == Rank.ACE);

        if (canBeSoft(sum, hasAce)) {
            return new Score(sum + ACE_ADJUST_AMOUNT);
        }
        return new Score(sum);
    }

    private boolean canBeSoft(int sum, boolean hasAce) {
        return hasAce && !bustPolicy.isBust(new Score(sum + ACE_ADJUST_AMOUNT));
    }
}
