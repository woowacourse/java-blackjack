package blackjack.model.state.running;

import blackjack.model.card.Card;
import blackjack.model.state.Hand;
import blackjack.model.state.State;
import blackjack.model.state.finished.Stand;
import java.util.List;

public abstract sealed class RunningState
        implements State
        permits InitialDeal, DealerDrawing, Hit {

    protected static final int ONE = 1;
    protected static final int BLACKJACK_CARD_COUNT = 2;
    protected static final int MAX_SCORE = 21;

    protected final Hand hand;

    public RunningState(Hand hand) {
        this.hand = hand;
    }

    @Override
    public State stand() {
        return new Stand(hand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public List<Card> getHandCards() {
        return hand.getCards();
    }

    public int getTotal() {
        return hand.getTotal();
    }
}
