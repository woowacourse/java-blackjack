package blackjack.domain.state.finished;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.Created;
import blackjack.domain.state.State;

public abstract class Finished extends Created {

    Finished(final Cards cards) {
        super(cards);
    }

    @Override
    public State draw(final Card card) {
        throw new IllegalStateException("턴이 종료 된 플레이어는 카드를 받을 수 없습니다.");
    }

    @Override
    public State stay() {
        throw new IllegalStateException("턴이 종료 된 플레이어는 다시 턴을 종료할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
