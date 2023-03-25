package blackjack.domain.participants.status.stopped;

import blackjack.domain.game.ResultType;
import blackjack.domain.participants.Hand;
import blackjack.domain.participants.status.Status;

public class Blackjack extends Stopped {

    public Blackjack(final Hand cards) {
        super(cards);
    }

    @Override
    public ResultType findResultType(final Status opponent) {
        if (opponent.isBlackjack()) {
            return ResultType.TIE;
        }
        return ResultType.BLACKJACK_WIN;
    }

}
