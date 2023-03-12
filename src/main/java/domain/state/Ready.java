package domain.state;

import domain.card.Card;
import domain.game.Score;
import domain.user.Hand;

import java.util.List;

public class Ready implements State {

    private static final int READY_SCORE = 0;

    @Override
    public Score score() {
        return new Score(READY_SCORE);
    }

    @Override
    public State draw(Card card) {
        return new Hit(card);
    }

    @Override
    public double profit(int base) {
        throw new IllegalStateException("수익을 계산할 수 없는 상태입니다.");
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
