package blackjack.model;

import java.util.List;

public class ScoreCalculator {
    private static final int BLACKJACK_SCORE = 21;
    private static final int ACE_ADJUST_AMOUNT = 10;

    public Score calculate(List<Card> cards) {
        int sum = cards.stream().mapToInt(Card::getValue).sum();
        boolean hasAce = cards.stream().anyMatch(x -> x.rank() == Rank.ACE);

        boolean canBeSoft = canBeSoft(sum, hasAce);
        if (canBeSoft) {
            return new Score(sum + ACE_ADJUST_AMOUNT);
        }
        return new Score(sum);
    }

    private boolean canBeSoft(int sum, boolean hasAce) {
        return hasAce && (sum + ACE_ADJUST_AMOUNT) <= BLACKJACK_SCORE;
    }
}
