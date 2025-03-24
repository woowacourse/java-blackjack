package blackjack.blackjack.state.finished;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.StateType;

public final class Bust extends Finished {

    public Bust(final Hand hand) {
        super(hand, StateType.BUST);
    }
}
