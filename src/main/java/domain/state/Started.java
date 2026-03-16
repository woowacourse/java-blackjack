package domain.state;

import domain.member.Hand;

public abstract class Started implements State {
    protected final Hand hand;

    protected Started(Hand hand) {
        this.hand = hand;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public Hand hand() {
        return hand;
    }
}
