package domain;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Hand {

    private final Set<Card> cards;

    public Hand() {
        this(Collections.emptySet());
    }

    public Hand(Collection<Card> cards) {
        this.cards = new HashSet<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public Set<Card> cards() {
        return new HashSet<>(cards);
    }

    public int score() {
        int scoreSum = sumScoreOf(cards);
        if (hasAIn(cards) && canAddTenTo(scoreSum)) {
            scoreSum = scoreSum + 10;
        }
        return scoreSum;
    }

    private int sumScoreOf(Set<Card> cards) {
        return cards.stream()
                .mapToInt(Card::score)
                .sum();
    }

    private boolean hasAIn(Set<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isA);
    }

    private boolean canAddTenTo(int score) {
        return score + 10 <= 21;
    }

    public Result compareWith(Hand other) {
        if ((isBust() && other.isBust()) || score() == other.score()) {
            return Result.DRAW;
        }
        if (!isBust() && (other.isBust() || score() > other.score())) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    public boolean isBust() {
        return score() > 21;
    }

    public boolean isDrawAgainst(Hand other) {
        if (this.isBust() && other.isBust()) {
            return true;
        }
        return this.hasSameScoreWith(other);
    }

    public boolean isWinnerAgainst(Hand other) {
        if (this.isBust()) {
            return false;
        }
        return other.isBust() || this.hasScoreGreaterThan(other);
    }

    private boolean hasSameScoreWith(Hand other) {
        return score() == other.score();
    }

    private boolean hasScoreGreaterThan(Hand other) {
        return score() > other.score();
    }
}
