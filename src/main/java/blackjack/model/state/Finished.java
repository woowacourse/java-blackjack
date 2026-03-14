package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.Hand;

public abstract class Finished extends BlackjackState {

    protected Finished(Hand hand) {
        super(hand);
    }

    @Override
    public BlackjackState hit(Card newCard) {
        throw new IllegalStateException("힛이 불가능한 상태입니다.");
    }

    @Override
    public final boolean canHit() {
        return false;
    }
}
