package blackjack.model.hand;

import blackjack.model.card.Card;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Hand {

    private static final int BUST_LOWER_BOUND = 22;
    private static final int ACE_ADJUST_SCORE = 10;

    protected final Collection<Card> cards;

    protected Hand() {
        this.cards = List.of();
    }

    protected Hand(Collection<Card> cards) {
        this.cards = List.copyOf(cards);
    }

    public Collection<Card> getCards() {
        return List.copyOf(cards);
    }

    public Hand hit(Card card) {
        Collection<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);

        return nextState(newCards);
    }

    public abstract boolean canHit();

    public double getEarningRate() {
        return 1;
    }

    public boolean isBust() {
        return isBust(calculateScore());
    }

    protected boolean isBust(Collection<Card> cards) {
        return isBust(calculateScore(cards));
    }

    public int calculateScore() {
        return calculateScore(cards);
    }

    protected int calculateScore(Collection<Card> cards) {
        int scoreBeforeAdjust = getScoreBeforeAdjust(cards);

        return adjust(scoreBeforeAdjust, cards);
    }

    protected abstract Hand nextState(Collection<Card> cards);

    private boolean isBust(int score) {
        return score >= BUST_LOWER_BOUND;
    }

    private int getScoreBeforeAdjust(Collection<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int adjust(int scoreBeforeAdjust, Collection<Card> cards) {
        boolean containAce = cards.stream()
                .anyMatch(Card::isAce);
        int scoreAfterAdjust = scoreBeforeAdjust + ACE_ADJUST_SCORE;

        if (isNotBust(scoreAfterAdjust) && containAce) {
            return scoreAfterAdjust;
        }

        return scoreBeforeAdjust;
    }

    private boolean isNotBust(int score) {
        return !isBust(score);
    }
}
