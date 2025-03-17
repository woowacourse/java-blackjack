package domain.state;

import domain.deck.Card;
import domain.gamer.Hand;

public abstract class Finished extends Started {

    public Finished(final Hand hand) {
        super(hand);
    }

    @Override
    public State hit(final Card card) {
        throw new IllegalArgumentException("더 이상 카드를 받을 수 없습니다. 이미 종료된 상태입니다.");
    }

    @Override
    public State stay() {
        throw new IllegalArgumentException("이미 종료된 상태입니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
