package domain.state;

import domain.card.Card;
import domain.participant.Hand;

public abstract class Finished extends Started {
    protected Finished(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException("종료된 상태에서는 카드를 더 뽑을 수 없습니다.");
    }

    @Override
    public State stay() {
        throw new IllegalStateException("이미 종료된 상태입니다.");
    }
}
