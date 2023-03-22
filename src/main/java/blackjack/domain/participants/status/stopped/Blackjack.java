package blackjack.domain.participants.status.stopped;

import blackjack.domain.game.ResultType;
import blackjack.domain.game.Score;
import blackjack.domain.participants.Hand;
import blackjack.domain.participants.status.Status;

public class Blackjack extends Stopped {
    public Blackjack(Hand cards) {
        super(cards);
    }

    @Override
    public ResultType findResultType(Status opponent) {
        Score scoreOfOpponent = opponent.score();
        if (scoreOfOpponent.isMax()) {
            return ResultType.TIE;
        }
        return ResultType.BLACKJACK_WIN;
    }

}
