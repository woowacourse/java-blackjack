package blackjack.domain.participants.status.stopped;

import blackjack.domain.card.Card;
import blackjack.domain.game.ResultType;
import blackjack.domain.participants.Hand;
import blackjack.domain.participants.status.Started;
import blackjack.domain.participants.status.Status;

public abstract class Stopped extends Started {

    protected Stopped(Hand cards) {
        super(cards);
    }

    @Override
    public Status addCard(Card card) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Status stay() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public abstract ResultType findResultType(Status opponent);

}
