package domain.state;

import domain.Hand;
import domain.WinningStatus;
import domain.card.Card;
import exception.ErrorMessage;

public final class BlackJack extends Started {

    public BlackJack(Hand hand) {
        super(hand);
    }

    @Override
    public ParticipantState draw(Card card) {
        throw new IllegalStateException(ErrorMessage.CANNOT_HIT_ON_BLACKJACK.getMessage());
    }

    @Override
    public WinningStatus calculateWinningStatus(ParticipantState dealerState) {
        if (dealerState.isBlackJack()) {
            return WinningStatus.DRAW;
        }
        return WinningStatus.BLACKJACK_WIN;
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
    public boolean isBlackJack(){
        return true;
    }
}
