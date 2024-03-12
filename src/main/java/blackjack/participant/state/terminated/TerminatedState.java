package blackjack.participant.state.terminated;

import blackjack.card.Card;
import blackjack.participant.Hand;
import blackjack.participant.state.GameState;

public abstract class TerminatedState extends GameState {

    protected TerminatedState(Hand hand) {
        super(hand);
    }

    @Override
    public GameState drawCard(Card card) {
        throw new UnsupportedOperationException("현재 상태에서는 드로우 여부를 결정할 수 없습니다.");
    }

    @Override
    public GameState stand() {
        throw new UnsupportedOperationException("현재 상태에서는 스탠드 여부를 결정할 수 없습니다.");
    }

    @Override
    public boolean isTerminated() {
        return true;
    }
}
