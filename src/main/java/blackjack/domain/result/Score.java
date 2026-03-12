package blackjack.domain.result;

import blackjack.domain.card.Card;
import java.util.List;

public record Score(int value) implements Comparable<Score> {
    private static final int ACE_ADJUST_AMOUNT = 10;
    private static final int BUST_THRESHOLD = 21;

    public static Score from(List<Card> cards) {
        return new Score(calculateOptimalTotalOf(cards));
    }

    public static int calculateOptimalTotalOf(List<Card> cards) {
        int sum = calculateSum(cards);
        boolean containsAce = containsAce(cards);
        if (containsAce && canApplyAceAmount(sum)) {
            return sum + ACE_ADJUST_AMOUNT;
        }
        return sum;
    }

    private static int calculateSum(List<Card> cards) {
        return cards.stream().mapToInt(Card::getValue).sum();
    }

    private static boolean containsAce(List<Card> cards) {
        return cards.stream().anyMatch(Card::isAce);
    }

    private static boolean canApplyAceAmount(final int sum) {
        return (sum + ACE_ADJUST_AMOUNT) <= BUST_THRESHOLD;
    }

    public boolean isBust() {
        return this.value > BUST_THRESHOLD;
    }

    @Override
    public int compareTo(Score other) {
        return Integer.compare(this.value, other.value);
    }
}
