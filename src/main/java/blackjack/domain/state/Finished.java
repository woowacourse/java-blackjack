package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public abstract class Finished extends Started {

    protected Finished(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException("[ERROR] 카드뽑는걸 지원하지 않습니다.");
    }

    @Override
    public State stay() {
        throw new UnsupportedOperationException("[ERROR] 스테이를 지원하지 않습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
