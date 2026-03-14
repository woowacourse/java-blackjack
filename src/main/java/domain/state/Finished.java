package domain.state;

import domain.card.Card;
import domain.member.Hand;

public abstract class Finished extends Started {

    public Finished(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State draw(Card card) {
        throw new IllegalArgumentException("이미 종료된 상태입니다.");
    }

    @Override
    public State stay() {
        return this;
    }
}
