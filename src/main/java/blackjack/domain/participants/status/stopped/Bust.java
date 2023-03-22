package blackjack.domain.participants.status.stopped;

import blackjack.domain.game.ResultType;
import blackjack.domain.game.Score;
import blackjack.domain.participants.Hand;
import blackjack.domain.participants.status.Status;

public class Bust extends Stopped {

    public Bust(Hand cards) {
        super(cards);
    }

    @Override
    public ResultType findResultType(Status opponent) {
        Score scoreOfOpponent = opponent.score();
        if (scoreOfOpponent.isOverThanMax()) {
            return ResultType.TIE;
        }

        return ResultType.LOSE;
    }

}
