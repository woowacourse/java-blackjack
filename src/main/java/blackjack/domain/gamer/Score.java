package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import java.util.List;

public class Score {

    private static final int BLACKJACK_MAX_SCORE = 21;
    private static final int BLACKJACK_HAND_SIZE_CONDITION = 2;
    private static final int ACE_VALUE_MODIFIER = 10;

    private final int value;
    private final int numberOfCards;

    public Score(List<Card> cards) {
        this.value = calculateScore(cards);
        this.numberOfCards = cards.size();
    }

    Score(int value, int numberOfCards) {
        this.value = value;
        this.numberOfCards = numberOfCards;
    }

    public int calculateScore(List<Card> cards) {
        int sum = sumScore(cards);
        for (int i = 0; i < countAce(cards); i++) {
            sum = adjust(sum);
        }
        return sum;
    }

    private int sumScore(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getNumberValue)
                .sum();
    }

    private int countAce(List<Card> cards) {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int adjust(int sum) {
        if (sum > BLACKJACK_MAX_SCORE) {
            sum -= ACE_VALUE_MODIFIER;
        }
        return sum;
    }

    public boolean isBlackjack() {
        return value == BLACKJACK_MAX_SCORE && numberOfCards == BLACKJACK_HAND_SIZE_CONDITION;
    }

    public boolean isNotBlackjack() {
        return !isBlackjack();
    }

    public boolean isBust() {
        return value > BLACKJACK_MAX_SCORE;
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public boolean isHigherThan(Score other) {
        return value > other.getValue();
    }

    public boolean isLowerThan(Score other) {
        return value < other.getValue();
    }

    public int getValue() {
        return value;
    }
}
