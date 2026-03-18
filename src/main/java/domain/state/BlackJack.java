package domain.state;

import domain.Hand;
import domain.WinningStatus;
import domain.card.Card;

public final class BlackJack extends Started {

    public BlackJack(Hand hand) {
        super(hand);
    }

    @Override
    public ParticipantState draw(Card card) {
        throw new IllegalStateException("BlackJack 상태에서는 hit할 수 없습니다.");
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
