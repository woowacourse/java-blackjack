package domain.state;

import domain.Hand;
import domain.WinningStatus;
import domain.card.Card;
import exception.ErrorMessage;

public final class Hit extends Started {

    public Hit(Hand hand) {
        super(hand);
    }

    @Override
    public ParticipantState draw(Card card) {
        hand.addCard(card);
        if (hand.isBust()) {
            return new Bust(hand);
        }
        if (hand.isBlackJack()) {
            return new BlackJack(hand);
        }
        if (hand.calculateScore() == Hand.BLACKJACK_SCORE) {
            return new Stay(hand);
        }
        return this;
    }

    @Override
    public WinningStatus calculateWinningStatus(ParticipantState dealerState){
        throw new IllegalStateException(ErrorMessage.CANNOT_CALCULATE_WINNING_STATUS_ON_HIT.getMessage());
    }

    @Override
    public ParticipantState onStay() {
        return new Stay(hand);
    }
}
