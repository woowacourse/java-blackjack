package blackjack.blackjack.state.finished;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.StateType;
import blackjack.blackjack.state.started.StartState;

public abstract class FinishedState extends StartState {

    public FinishedState(final Hand hand, final StateType stateType) {
        super(hand, stateType);
    }

    @Override
    public final State receiveCards(Hand hand) {
        throw new IllegalStateException("[ERROR] 종료된 게임에서는 카드를 받을 수 없습니다.");
    }

    @Override
    public final State stay() {
        return this;
    }

    @Override
    public final boolean isNotFinished() {
        return false;
    }
}
