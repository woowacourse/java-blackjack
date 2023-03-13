package blackjackgame.domain.state;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.Hand;

public abstract class Finished extends State {

    Finished(Hand hand) {
        super(hand);
    }

    @Override
    public final State draw(Card card) {
        throw new IllegalStateException("새로운 카드를 뽑을 수 없습니다.");
    }

    @Override
    public final State stay() {
        throw new IllegalStateException("이미 게임이 끝나서 stay 할 수 없습니다.");
    }
}
