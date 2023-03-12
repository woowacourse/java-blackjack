package domain.hand;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import domain.card.Card;

public class Hand {

    private static final int BLACKJACK_CARD_SIZE = 2;

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

    public Score score() {
        Score scoreSum = sumScoreOf(cards);
        if (hasAIn(cards) && scoreSum.canAddBonus()) {
            return scoreSum.add(Score.BONUS);
        }
        return scoreSum;
    }

    private Score sumScoreOf(Set<Card> cards) {
        return cards.stream()
                .map(Card::getScore)
                .reduce(Score.ZERO, Score::add);
    }

    private boolean hasAIn(Set<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isA);
    }

    public boolean isDrawAgainst(Hand other) {
        Score score = this.score();
        Score otherScore = other.score();
        if (this.isBlackjack() || other.isBlackjack()) {
            return this.isBlackjack() && other.isBlackjack();
        }
        if (score.isBust() && otherScore.isBust()) {
            return true;
        }
        return score.isSameWith(otherScore);
    }

    public boolean isWinnerAgainst(Hand other) {
        Score score = this.score();
        Score otherScore = other.score();
        if (this.isBlackjack() && !other.isBlackjack()) {
            return true;
        }
        if (score.isBust()) {
            return false;
        }
        return otherScore.isBust() || score.isGreaterThan(otherScore);
    }

    public boolean isBlackjack() {
        if (cards.size() == BLACKJACK_CARD_SIZE) {
            return score().isSameWith(Score.BLACKJACK);
        }
        return false;
    }

    public Set<Card> getCards() {
        return new HashSet<>(cards);
    }
}