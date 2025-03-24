package blackjack.blackjack.state.finished;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.StateType;
import blackjack.blackjack.state.started.Started;

public abstract class Finished extends Started {

    public Finished(final Hand hand, final StateType stateType) {
        super(hand, stateType);
    }

    @Override
    public State receiveCards(Hand hand) {
        throw new IllegalStateException("[ERROR] 종료된 게임에서는 카드를 받을 수 없습니다.");
    }

    @Override
    public State stay() {
        return this;
    }

    @Override
    public boolean isNotFinished() {
        return false;
    }
}
