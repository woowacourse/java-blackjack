package blackjack.domain.state.finished;

import blackjack.domain.Response;
import blackjack.domain.cards.Card;
import blackjack.domain.cards.Hand;
import blackjack.domain.state.State;

public abstract class Finished implements State {

    private final Hand hand;

    public Finished(Hand hand) {
        this.hand = hand;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException("카드를 뽑을 수 없는 상태입니다.");
    }

    @Override
    public Hand getHand() {
        return hand;
    }

    @Override
    public State moveStateByResponse(Response response) {
        throw new IllegalStateException("더 이상 진행할 수 없는 상태입니다.");
    }
}
