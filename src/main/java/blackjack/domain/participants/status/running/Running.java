package blackjack.domain.participants.status.running;

import blackjack.domain.game.ResultType;
import blackjack.domain.participants.Hand;
import blackjack.domain.participants.status.Started;
import blackjack.domain.participants.status.Status;

public abstract class Running extends Started {

    protected Running(Hand cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public ResultType findResultType(Status opponent) {
        throw new UnsupportedOperationException();
    }

}
