package domain.card;

import static domain.card.CardNumberType.ACE;

import java.util.List;

public record Score(int score) {
    private static final int NOT_BUST_LIMIT = 21;
    private static final int ACE_ADDING_VALUE = 10;
    private static final int ACE_VALUE_ADDING_AVAILABLE_SUM = 11;

    public static Score from(Hand hand) {
        int score = calculateScore(hand.getCards());
        return new Score(score);
    }

    private static int calculateScore(List<Card> cards) {
        int sum = cards.stream()
                .mapToInt(Card::getNumber)
                .sum();
        return addHighAceToSum(cards, sum);
    }

    private static int addHighAceToSum(List<Card> cards, int sum) {
        boolean hasAce = cards.stream()
                .anyMatch(card -> card.cardNumberType() == ACE);
        if (hasAce && sum <= ACE_VALUE_ADDING_AVAILABLE_SUM) {
            sum += ACE_ADDING_VALUE;
        }
        return sum;
    }

    public boolean isBust() {
        return score > NOT_BUST_LIMIT;
    }

    public boolean isGreaterThen(Score other) {
        return score > other.score;
    }

    public boolean isLessOrEqualThen(int targetValue) {
        return score <= targetValue;
    }

    public boolean isEqualTo(Score other) {
        return score == other.score;
    }
}
