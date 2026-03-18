package domain.state;

import domain.Hand;
import domain.WinningStatus;
import domain.card.Card;
import exception.ErrorMessage;

public final class Stay extends Started {

    public Stay(Hand hand) {
        super(hand);
    }

    @Override
    public ParticipantState draw(Card card) {
        throw new IllegalStateException(ErrorMessage.CANNOT_HIT_ON_STAY.getMessage());
    }

    @Override
    public WinningStatus calculateWinningStatus(ParticipantState dealerState) {
        if (dealerState.isBust()) {
            return WinningStatus.WIN;
        }
        if (dealerState.isBlackJack()) {
            return WinningStatus.LOSE;
        }
        return compareScore(dealerState.getScore());
    }

    private WinningStatus compareScore(int otherScore) {
        if (this.getScore() > otherScore) {
            return WinningStatus.WIN;
        }
        if (this.getScore() < otherScore) {
            return WinningStatus.LOSE;
        }
        return WinningStatus.DRAW;
    }

    @Override
    public ParticipantState onStay() {
        return this;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
