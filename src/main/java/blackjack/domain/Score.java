package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import java.util.List;
import java.util.Objects;

public class Score implements Comparable<Score> {

    private static final int MAX_SCORE = 21;
    private static final int TEN = 10;
    private static final int DEALER_ADD_CARD_BOUNDARY = 17;
    public static final Score BLACKJACK_SCORE = new Score(MAX_SCORE);

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public static Score of(List<Card> cards) {
        int score = calculate(cards);
        return new Score(score);
    }

    public static int calculate(List<Card> cards) {
        int sum = calculateWithoutAce(cards) + Denomination.ACE.getValue() * countAce(cards);
        if (containsAce(cards)) {
            sum = calculateWithAce(sum);
        }
        return sum;
    }

    private static int calculateWithAce(int sum) {
        if (sum + TEN > MAX_SCORE) {
            return sum;
        }
        return sum + TEN;
    }

    private static int calculateWithoutAce(List<Card> cards) {
        return cards.stream()
                .filter(card -> !Denomination.isAce(card.getCardValue()))
                .map(Card::getValue)
                .reduce(0, Integer::sum);
    }

    private static boolean containsAce(List<Card> cards) {
        return cards.stream()
                .map(Card::getCardValue)
                .anyMatch(Denomination::isAce);
    }

    private static int countAce(List<Card> cards) {
        return (int) cards.stream()
                .filter(card -> Denomination.isAce(card.getCardValue()))
                .count();
    }

    public Score plusTenIfNotBust() {
        final Score score = new Score(value + TEN);
        if (score.isBusted()) {
            return this;
        }
        return score;
    }

    public boolean isBusted() {
        return value > MAX_SCORE;
    }

    public boolean isMaxScore() {
        return value == MAX_SCORE;
    }

    public boolean dealerAbleToAdd() {
        return value < DEALER_ADD_CARD_BOUNDARY;
    }

    public boolean isUnderMaxScore() {
        return value < MAX_SCORE;
    }


    @Override
    public int compareTo(Score o) {
        return this.value - o.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public int getValue() {
        return value;
    }
}
