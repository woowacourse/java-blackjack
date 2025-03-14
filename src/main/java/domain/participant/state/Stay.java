package domain.participant.state;

import domain.card.TrumpCard;
import domain.participant.ParticipantHand;

public class Stay extends HandState {
    private static final double WINNER_PROFIT_RATE = 2.0;

    public Stay(ParticipantHand hand) {
        super(hand);
    }

    @Override
    public HandState addCard(TrumpCard card) {
        throw new IllegalArgumentException("Stay 상태에서는 카드를 추가할 수 없습니다.");
    }

    @Override
    public HandState stay() {
        return this;
    }

    @Override
    public double calculateProfit() {
        return WINNER_PROFIT_RATE;
    }
}
