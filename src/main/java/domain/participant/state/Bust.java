package domain.participant.state;

import domain.card.TrumpCard;
import domain.participant.ParticipantHand;

public class Bust extends HandState {

    public Bust(ParticipantHand hand) {
        super(hand);
    }

    @Override
    public HandState addCard(TrumpCard card) {
        throw new IllegalArgumentException("버스트 상태에서 카드를 추가할 수 없습니다.");
    }

    @Override
    public HandState stay() {
        throw new IllegalArgumentException("버스트 상태입니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double calculateProfitRate(HandState other) {
        return 0;
    }
}
