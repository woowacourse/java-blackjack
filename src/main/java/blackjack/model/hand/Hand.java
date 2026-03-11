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
        this.cards = new ArrayList<>();
    }

    protected Hand(Collection<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Collection<Card> getCards() {
        return List.copyOf(cards);
    }

    public Hand hit(Card card) {
        cards.add(card);

        return nextState();
    }

    public abstract boolean canHit();

    public double getEarningRate() {
        return 1;
    }

    public boolean isBust() {
        return isBust(calculateScore());
    }

    public boolean isBust(int score) {
        return score >= BUST_LOWER_BOUND;
    }

    public int calculateScore() {
        int scoreBeforeAdjust = getScoreBeforeAdjust();

        return adjust(scoreBeforeAdjust, cards);
    }

    protected abstract Hand nextState();

    private int getScoreBeforeAdjust() {
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
