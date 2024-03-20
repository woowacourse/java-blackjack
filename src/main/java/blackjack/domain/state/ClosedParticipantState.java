package blackjack.domain.state;

import blackjack.domain.card.Deck;

public abstract class ClosedParticipantState extends ParticipantState {

    ClosedParticipantState(Hand hand) {
        super(hand);
    }

    @Override
    public ParticipantState draw(Deck deck) {
        throw new UnsupportedOperationException("턴이 종료되어 드로우할 수 없는 상태입니다.");
    }

    @Override
    public ParticipantState stand() {
        throw new UnsupportedOperationException("턴이 종료되어 스탠드할 수 없는 상태입니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
