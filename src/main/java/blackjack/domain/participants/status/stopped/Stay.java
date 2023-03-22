package blackjack.domain.participants.status.stopped;

import blackjack.domain.game.ResultType;
import blackjack.domain.game.Score;
import blackjack.domain.participants.Hand;
import blackjack.domain.participants.status.Status;

public class Stay extends Stopped {

    public Stay(Hand cards) {
        super(cards);
    }

    @Override
    public ResultType findResultType(Status opponent) {
        Score scoreOfOpponent = opponent.score();
        if (scoreOfOpponent.isOverThanMax()) {
            return ResultType.WIN;
        }
        if (scoreOfOpponent.isMax()) {
            return ResultType.LOSE;
        }
        return findResultTypeWhenOpponentStay(scoreOfOpponent);
    }

    public ResultType findResultTypeWhenOpponentStay(Score scoreOfOpponent) {
        if (score().isBiggerThan(scoreOfOpponent)) {
            return ResultType.WIN;
        }
        if (scoreOfOpponent.isBiggerThan(score())) {
            return ResultType.LOSE;
        }
        return ResultType.TIE;
    }


}
