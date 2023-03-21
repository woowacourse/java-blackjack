package domain.state;

import domain.card.Card;
import domain.card.Hand;

public abstract class Finished extends State {
    Finished(Hand hand) {
        super(hand);
    }

    @Override
    public final State draw(Card card) {
        throw new IllegalStateException("[ERROR] 게임이 종료되어 카드를 뽑을 수 없습니다.");
    }

    @Override
    public final State stay() {
        throw new IllegalStateException("[ERROR] 게임이 종료되어 상태를 조작할 수 없습니다.");
    }

    @Override
    public final boolean isFinished() {
        return true;
    }
}
