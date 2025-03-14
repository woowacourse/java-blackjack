package domain.participant.state;

import domain.card.TrumpCard;
import domain.participant.ParticipantHand;

public class Start extends HandState {
    public Start(TrumpCard... initCards) {
        super(new ParticipantHand());
        for (TrumpCard initCard : initCards) {
            hand.addCard(initCard);
        }
    }

    @Override
    public HandState addCard(TrumpCard card) {
        throw new IllegalStateException("카드를 추가할 수 없습니다.");
    }

    @Override
    public HandState stay() {
        throw new IllegalStateException("STAY할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double calculateProfitRate(HandState other) {
        throw new IllegalStateException("시작 상태에서는 이익률을 계산할 수 없습니다.");
    }
}
