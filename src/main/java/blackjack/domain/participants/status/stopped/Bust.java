package blackjack.domain.participants.status.stopped;

import blackjack.domain.game.ResultType;
import blackjack.domain.participants.Hand;
import blackjack.domain.participants.status.Status;

public class Bust extends Stopped {

    public Bust(final Hand cards) {
        super(cards);
    }

    @Override
    public ResultType findResultType(final Status opponent) {
        return ResultType.LOSE;
    }

}
