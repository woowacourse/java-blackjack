package domain.state;

import domain.BlackjackRule;
import domain.Hand;
import domain.WinningStatus;
import domain.card.Card;

public final class Hit extends Started {
    public static final int BLACKJACK_HAND_SIZE = 2;

    public Hit(Hand hand) {
        super(hand);
    }

    @Override
    public ParticipantState draw(Card card) {
        hand.addCard(card);
        if (hand.getSum() > BlackjackRule.BLACKJACK_SCORE) {
            return new Bust(hand);
        }
        if (hand.getSize() == BLACKJACK_HAND_SIZE && hand.getSum() == BlackjackRule.BLACKJACK_SCORE) {
            return new BlackJack(hand);
        }
        if (hand.getSum() == BlackjackRule.BLACKJACK_SCORE) {
            return new Stay(hand);
        }
        return this;
    }

    @Override
    public WinningStatus calculateWinningStatus(ParticipantState dealerState){
        throw new IllegalStateException("Hit 상태에서는 승패를 계산할 수 없습니다.");
    }

    @Override
    public ParticipantState onStay() {
        return new Stay(hand);
    }
}
