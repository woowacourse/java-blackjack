package blackjackgame.domain.state;

import java.util.List;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.Hand;

public final class Ready extends State {

    public Ready() {
        super(new Hand());
    }

    @Override
    public State draw(Card card) {
        return new Hit(new Hand(card));
    }

    @Override
    public List<Card> cards() {
        throw new UnsupportedOperationException("카드가 존재하지 않습니다.");
    }

    @Override
    public State stay() {
        throw new IllegalStateException("게임을 멈출 수 없는 상태입니다.");
    }
}
