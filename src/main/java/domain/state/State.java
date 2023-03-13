package domain.state;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Score;

import java.util.List;

public abstract class State {
    private final Hand hand;

    protected State(Hand hand) {
        this.hand = hand;
    }

    public abstract State draw(Card card);

    public abstract State stay();

    public abstract boolean isFinished();

    public abstract int calculatePrize(int base);

    public final boolean isBust() {
        return hand.isBust();
    }

    public final boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public final List<Card> getCards() {
        return hand.getCards();
    }

    public final Score getScore() {
        return hand.calculateScore();
    }

    protected final Hand getHand() {
        return hand;
    }
}
