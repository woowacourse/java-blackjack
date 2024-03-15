package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import java.util.List;

public class HandValue {

    private static final int BLACKJACK_MAX_SCORE = 21;
    private static final int BLACKJACK_HAND_SIZE_CONDITION = 2;
    private static final int ACE_VALUE_MODIFIER = 10;

    private final int score;
    private final int handSize;

    public HandValue(List<Card> cards) {
        this.score = calculateScore(cards);
        this.handSize = cards.size();
    }

    HandValue(int score, int handSize) {
        this.score = score;
        this.handSize = handSize;
    }

    public int calculateScore(List<Card> cards) {
        int sum = cards.stream()
                .mapToInt(Card::getNumberValue)
                .sum();

        if (hasAce(cards)) {
            return adjustSumWithAce(cards, sum);
        }
        return sum;
    }

    private boolean hasAce(List<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    /**
     * ACE가 포함된 경우, 21 이하이면서 가장 가능한 큰 값으로 계산한다.
     */
    private int adjustSumWithAce(List<Card> cards, int sum) {
        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();

        for (int i = 0; i < aceCount; i++) {
            sum = adjust(sum);
        }
        return sum;
    }

    /**
     * Ace는 기본적으로 11로 계산되나, 합계가 21을 초과할 경우 1로 계산한다.
     */
    private int adjust(int sum) {
        if (sum > BLACKJACK_MAX_SCORE) {
            sum -= ACE_VALUE_MODIFIER;
        }
        return sum;
    }

    public boolean isBlackjack() {
        return score == BLACKJACK_MAX_SCORE && handSize == BLACKJACK_HAND_SIZE_CONDITION;
    }

    public boolean isNotBlackjack() {
        return !isBlackjack();
    }

    public boolean isBust() {
        return score > BLACKJACK_MAX_SCORE;
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public boolean isHigherThan(HandValue other) {
        return this.score > other.score;
    }

    public boolean isLowerThan(HandValue other) {
        return this.score < other.score;
    }

    public int getScore() {
        return score;
    }
}
