package domain.state;

import domain.Hand;
import domain.WinningStatus;
import domain.card.Card;
import exception.ErrorMessage;

public final class Bust extends Started {

    public Bust(Hand hand) {
        super(hand);
    }

    @Override
    public ParticipantState draw(Card card) {
        throw new IllegalStateException(ErrorMessage.CANNOT_HIT_ON_BUST.getMessage());
    }

    @Override
    public WinningStatus calculateWinningStatus(ParticipantState dealerState) {
        return WinningStatus.LOSE;
    }

    @Override
    public ParticipantState onStay() {
        return this;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isBust(){
        return true;
    }
}
