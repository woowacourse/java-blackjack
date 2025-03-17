package participant.value;

import static card.CardNumberType.ACE;
import static result.GameStatus.*;

import card.Card;
import java.util.List;
import result.GameStatus;

public record Score(int score) {
    private static final int BLACKJACK_VALUE = 21;
    private static final int ACE_ADDING_VALUE = 10;
    private static final int ACE_VALUE_ADDING_AVAILABLE_SUM = 11;

    public static Score from(List<Card> cards) {
        int score = calculateScore(cards);
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

    public GameStatus compare(Score other) {
        if (isGreaterThan(other)) {
            return WIN;
        }
        if (isEqualTo(other)) {
            return DRAW;
        }
        return LOSE;
    }

    public boolean isBust() {
        return score > BLACKJACK_VALUE;
    }

    public boolean isBlackJackValue() {
        return score == BLACKJACK_VALUE;
    }

    public boolean isGreaterThan(Score other) {
        return score > other.score;
    }

    public boolean isLessOrEqualThan(int targetValue) {
        return score <= targetValue;
    }

    public boolean isEqualTo(Score other) {
        return score == other.score;
    }
}
