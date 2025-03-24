package blackjack.blackjack.state.finished;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.StateType;

public final class BlackjackState extends FinishedState {

    public BlackjackState(final Hand cards) {
        super(cards, StateType.BLACKJACK);
    }
}
