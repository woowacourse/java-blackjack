package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hand {

    private final Set<Card> cards;

    public Hand() {
        this(Collections.emptySet());
    }

    public Hand(Collection<Card> cards) {
        this.cards = new HashSet<>(cards);
    }

    public Hand hit(Card card) {
        Set<Card> hitCards = new HashSet<>(cards);
        hitCards.add(card);
        return new Hand(hitCards);
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

    public List<Card> cards() {
        return new ArrayList<>(cards);
    }

    private boolean hasSameScoreWith(Hand other) {
        return score() == other.score();
    }

    private boolean hasScoreGreaterThan(Hand other) {
        return score() > other.score();
    }
}