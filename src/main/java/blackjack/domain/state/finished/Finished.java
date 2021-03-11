package blackjack.domain.state.finished;

import blackjack.domain.card.Cards;
import blackjack.domain.state.Started;
import blackjack.domain.state.State;

public abstract class Finished extends Started {

    public Finished(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State stay() {
        return this;
    }
}
