package domain.user.state;

import domain.card.Card;
import domain.user.Cards;

public abstract class Terminated extends State {

    protected Terminated(Cards cards) {
        super(cards);
    }

    @Override
    public State hit(Card card) {
        throw new UnsupportedOperationException("더 이상 카드를 받을 수 없습니다.");
    }

    @Override
    public boolean isHittable() {
        return false;
    }

    @Override
    public State stay() {
        throw new UnsupportedOperationException("이미 종료되었습니다.");
    }
}
