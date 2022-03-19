package blackjack.domain.state.finished;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;
import blackjack.domain.state.State;
import blackjack.domain.state.running.Running;

public abstract class Finished extends Running {

    protected Finished(Cards cards) {
        super(cards);
    }

    @Override
    public final State draw(Card card) {
        throw new IllegalStateException("Finished 상태에선 카드를 더 받을 수 없습니다.");
    }

    @Override
    public final State stay() {
        throw new IllegalStateException("Finished 상태에선 상태를 변경할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
