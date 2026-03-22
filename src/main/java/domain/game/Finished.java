package domain.game;

import domain.card.Card;
import domain.card.Hand;

public abstract class Finished extends Started {

    protected Finished(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public HandState draw(Card card) {
        throw new UnsupportedOperationException("완료된 상태에서는 카드를 뽑을 수 없습니다.");
    }

    @Override
    public HandState stay() {
        throw new UnsupportedOperationException("완료된 상태에서는 stay할 수 없습니다.");
    }
}