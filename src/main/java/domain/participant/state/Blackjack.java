package domain.participant.state;

import domain.card.TrumpCard;
import domain.participant.ParticipantHand;

public class Blackjack extends HandState {
    private static final double BLACKJACK_PROFIT = 2.5;
    private static final double BLACKJACK_DRAW_PROFIT = 1.0;

    public Blackjack(ParticipantHand hand) {
        super(hand);
    }

    @Override
    public HandState addCard(TrumpCard card) {
        throw new IllegalStateException("블랙잭 상태에서 카드를 추가할 수 없습니다.");
    }

    @Override
    public HandState stay() {
        throw new IllegalStateException("이미 블랙잭 상태입니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double calculateProfitRate(HandState other) {
        if (other.isBlackjack()) {
            return BLACKJACK_DRAW_PROFIT;
        }
        return BLACKJACK_PROFIT;
    }
}
