package blackjack.model.state;

import blackjack.model.card.Hand;

public abstract class Playing extends BlackjackState {

    protected Playing(Hand hand) {
        super(hand);
    }

    @Override
    public final boolean canHit() {
        return true;
    }
}
