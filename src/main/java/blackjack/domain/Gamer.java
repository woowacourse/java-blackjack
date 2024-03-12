package blackjack.domain;

import blackjack.domain.card.Hand;

public abstract class Gamer {

    protected static final int BLACKJACK = 21;

    protected final Hand hand;

    public Gamer(final Hand hand) {
        this.hand = hand;
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK;
    }

    public boolean isBlackjack() {
        return calculateScore() == BLACKJACK && hand.hasOnlyInitialCard();
    }

    public int calculateScore() {
        return hand.calculateOptimalSum();
    }

    abstract boolean canDraw();
}
