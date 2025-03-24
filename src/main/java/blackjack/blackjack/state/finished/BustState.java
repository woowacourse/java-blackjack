package blackjack.blackjack.state.finished;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.StateType;

public final class BustState extends FinishedState {

    public BustState(final Hand hand) {
        super(hand, StateType.BUST);
    }
}
