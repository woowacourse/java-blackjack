package domain.participant.state;

import domain.card.TrumpCard;
import domain.participant.ParticipantHand;

public class Hit extends HandState {
    public Hit(ParticipantHand hand) {
        super(hand);
    }

    @Override
    public HandState addCard(TrumpCard card) {
        hand.addCard(card);
        if (isBlackjack()) {
            return new Blackjack(hand);
        }
        if (isBust()) {
            return new Bust(hand);
        }
        return this;
    }

    @Override
    public HandState stay() {
        return new Stay(hand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double calculateProfitRate(HandState other) {
        throw new IllegalStateException("HIT 상태에서는 수익률을 계산할 수 없습니다.");
    }
}
