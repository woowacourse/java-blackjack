package domain.state;

import domain.card.Card;
import domain.card.Hand;

public abstract class Ready extends State {
    Ready(Hand hand) {
        super(hand);
    }

    @Override
    public State stay() {
        throw new IllegalStateException("[ERROR] 게임 준비 단계에는 상태를 조작할 수 없습니다.");
    }
}
