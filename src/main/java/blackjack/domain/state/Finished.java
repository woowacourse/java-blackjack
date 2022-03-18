package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Finished extends AbstractState {

    protected Finished(Cards cards) {
        super(cards);
    }

    @Override
    public State hit(Card card) {
        throw new UnsupportedOperationException("[ERROR] 게임을 진행할 수 없습니다.");
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("[ERROR] 게임을 진행할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
