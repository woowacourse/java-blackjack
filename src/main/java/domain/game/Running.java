package domain.game;

import domain.card.Hand;

public abstract class Running extends Started {

    protected Running(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Outcome calculateOutcome(HandState dealerState) {
        throw new UnsupportedOperationException("진행 중인 상태에서는 승패를 판단할 수 없습니다.");
    }

    @Override
    public Outcome outcomeForBlackjack() {
        throw new UnsupportedOperationException("진행 중인 상태에서는 승패를 판단할 수 없습니다.");
    }

    @Override
    public Outcome outcomeForStay(int playerScore) {
        throw new UnsupportedOperationException("진행 중인 상태에서는 승패를 판단할 수 없습니다.");
    }
}