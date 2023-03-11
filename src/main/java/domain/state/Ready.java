package domain.state;

import domain.card.Card;
import domain.user.Hand;

import java.util.List;

public class Ready implements State {

    @Override
    public State draw(Card card) {
        return new Hit(card);
    }

    @Override
    public State stay() {
        throw new IllegalStateException("게임을 멈출 수 없는 상태입니다.");
    }

    @Override
    public List<Card> cards() {
        throw new IllegalStateException("카드가 없습니다.");
    }

    @Override
    public Hand hand() {
        throw new IllegalStateException("카드가 없습니다.");
    }
}
