package domain.state;

import domain.card.Card;
import domain.card.Hand;

public abstract class Finished extends Started {

    public Finished(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException("[ERROR] Finished 상태에서는 카드를 더 이상 뽑을 수 없습니다.");
    }

    @Override
    public State stay() {
        throw new UnsupportedOperationException("[ERROR] Finished 상태에서는 stay를 할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
